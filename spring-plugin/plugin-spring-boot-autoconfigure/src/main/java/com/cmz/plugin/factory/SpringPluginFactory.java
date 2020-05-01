package com.cmz.plugin.factory;

import com.cmz.plugin.module.PluginConfig;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/29 16:46
 */

public interface SpringPluginFactory{

    /**
     * 装载指定插件
     *
     * @param pluginId
     */
    public void activePlugin(String pluginId);

    /**
     * 移除指定插件
     *
     * @param pluginId
     */
    public void disablePlugin(String pluginId);

    /**
     * 安装插件
     * @param plugin
     */
    public void installPlugin(PluginConfig plugin,Boolean load);

    /**
     * 卸载插件
     * @param plugin
     */
    public void uninstallPlugin(PluginConfig plugin);

    /**
     * 获取插件列表
     * @return
     */
    public List<PluginConfig> getPluginList();

}
