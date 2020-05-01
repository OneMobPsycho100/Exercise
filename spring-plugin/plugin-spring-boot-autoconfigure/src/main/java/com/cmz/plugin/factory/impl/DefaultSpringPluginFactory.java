package com.cmz.plugin.factory.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cmz.plugin.exception.PluginException;
import com.cmz.plugin.factory.SpringPluginFactory;
import com.cmz.plugin.module.PluginConfig;
import org.aopalliance.aop.Advice;
import org.apache.commons.io.FileUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/29 16:52
 */
@Aspect
public class DefaultSpringPluginFactory implements ApplicationContextAware, InitializingBean, SpringPluginFactory {

    private Logger logger = LoggerFactory.getLogger(DefaultSpringPluginFactory.class);

    private static final String BASE_DIR;
    private ApplicationContext applicationContext;
    private Map<String, Advice> adviceCache = new HashMap<>();
    private Map<String, PluginConfig> configMap = new HashMap<>();

    static {
        BASE_DIR = System.getProperty("user.home") + "/.plugin/";
    }

    @Pointcut()
    public void pointCut(){}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        this.loaderLocalPlugins();
    }

    private void loaderLocalPlugins() {
        try {
            Map<String, PluginConfig> localConfig = this.readerLocalConfigs();
            if (localConfig == null) {
                return;
            }
            configMap.putAll(localConfig);
            localConfig.values().stream()
                    .filter(PluginConfig::getActive)
                    .forEach(config -> activePlugin(config.getId()));
            logger.info("init spring-aop-plugin {}", localConfig);
        } catch (IOException e) {
            throw new PluginException("loader failed", e);
        }
    }

    private Map<String, PluginConfig> readerLocalConfigs() throws IOException {
        File configFile = new File(BASE_DIR + "plugin-config.json");
        if (!configFile.exists()) {
            return null;
        }
        String jsonStr = FileUtils.readFileToString(configFile, StandardCharsets.UTF_8);
        return JSONObject.parseObject(jsonStr,
                new TypeReference<Map<String, PluginConfig>>() {
                });
    }

    @Override
    public void activePlugin(String pluginId) {
        if (!configMap.containsKey(pluginId)) {
            throw new PluginException(String
                    .format("the specified plugin does not exist id=%s", pluginId));
        }
        PluginConfig config = configMap.get(pluginId);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(name);
            if (bean == this) {
                continue;
            }
            if (!(bean instanceof Advised)) {
                continue;
            }
            Advice advice = null;
            try {
                advice = buildAdvice(config);
                ((Advised) bean).addAdvice(advice);
            } catch (Exception e) {
                throw new PluginException("install failed", e);
            }
        }
    }

    private Advice buildAdvice(PluginConfig config) throws Exception {
        String className = config.getClassName();
        if (adviceCache.containsKey(className)) {
            return adviceCache.get(className);
        }

        String remoteUrl = config.getJarRemoteUrl();
        File jar = new File(getLocationJarFile(remoteUrl));
        if (!jar.exists()) {
            URL url = new URL(remoteUrl);
            try (InputStream inputStream = url.openStream()) {
                if (!jar.getParentFile().mkdirs()) {
                    throw new PluginException("directory creation failed");
                }
                Files.copy(inputStream, jar.toPath());
            } catch (Exception e) {
                e.printStackTrace();
                jar.deleteOnExit();
            }
        }

        // 将jar 加载到classLoader
        URLClassLoader loader = (URLClassLoader) getClass().getClassLoader();
        URL targetUrl = jar.toURI().toURL();
        boolean isLoader = Arrays.asList(loader.getURLs()).contains(targetUrl);
        if (!isLoader) {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            add.setAccessible(true);
            add.invoke(loader, targetUrl);
        }

        // 初始化 plugin 实例化 advice
        Class<?> adviceClass = loader.loadClass(config.getClassName());
        if (!Advice.class.isAssignableFrom(adviceClass)) {
            throw new PluginException(String.format("plugin配置错误 %s非 %s的实现"
                    , config.getClassName(), Advice.class.getName()));
        }
        adviceCache.put(adviceClass.getName(), (Advice) adviceClass.newInstance());
        return adviceCache.get(adviceClass.getName());
    }

    private String getLocationJarFile(String remoteUrl) {
        String jarName = remoteUrl.substring(remoteUrl.lastIndexOf("/"));
        return BASE_DIR + jarName;
    }

    @Override
    public void disablePlugin(String pluginId) {

    }

    @Override
    public void installPlugin(PluginConfig plugin, Boolean load) {
        String pluginId = plugin.getId();
        if (configMap.containsKey(pluginId)) {
            throw new PluginException(String
                    .format("The plugin already exists id=%s", pluginId));
        }

        configMap.put(pluginId, plugin);

        // 下载远程插件
        try {
            buildAdvice(plugin);
        } catch (Exception e) {
            configMap.remove(pluginId);
            throw new PluginException(String
                    .format("plugin build failed id=%s", pluginId));
        }

        // 保存配置到本地
        try {
            storeConfigs();
        } catch (IOException e) {
            configMap.remove(pluginId);
            throw new PluginException(String
                    .format("plugin build failed id=%s", pluginId));
        }

        // 安装插件
        if (load) {
            activePlugin(pluginId);
        }
    }

    /**
     * 保存配置到本地
     *
     * @throws IOException
     */
    private void storeConfigs() throws IOException {
        File configFile = new File(BASE_DIR + "pluginconfigs.json");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            if (!configFile.createNewFile()) {
                throw new PluginException("local configFile create failed");
            }
        }
        Map<String, PluginConfig> storeConfig = new HashMap<>(configMap);
        String jsonString = JSONObject.toJSONString(storeConfig);
        try (OutputStream out = new FileOutputStream(configFile)) {
            out.write(jsonString.getBytes());
        }

    }

    @Override
    public void uninstallPlugin(PluginConfig plugin) {

    }

    @Override
    public List<PluginConfig> getPluginList() {
        return null;
    }

}
