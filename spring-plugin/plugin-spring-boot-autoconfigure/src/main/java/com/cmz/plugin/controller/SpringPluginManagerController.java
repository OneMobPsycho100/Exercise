package com.cmz.plugin.controller;

import com.cmz.plugin.factory.SpringPluginFactory;
import com.cmz.plugin.module.PluginConfig;
import com.cmz.plugin.module.PluginSite;
import com.cmz.plugin.module.Result;
import com.cmz.plugin.utils.PluginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/29 16:55
 */
@RestController
@RequestMapping("/plugin")
public class SpringPluginManagerController {

    @Autowired
    private SpringPluginFactory pluginFactory;

    @RequestMapping("/active")
    public Result<String> activePlugin(@RequestParam String id) {
        pluginFactory.activePlugin(id);
        return Result.getSuccess();
    }

    @RequestMapping("/install")
    public Result<String> installPlugin(@RequestParam String url, @RequestParam String id) throws IOException {
        PluginUtil.getSite(url)
                .getConfigs()
                .stream()
                .filter(c -> id.equals(c.getId()))
                .findFirst()
                .ifPresent(c -> pluginFactory.installPlugin(c, true));
        return Result.getSuccess();
    }

    @RequestMapping("/uninstall")
    public Result<String> uninstallPlugin(@RequestParam String id) {
        pluginFactory.uninstallPlugin(id);
        return Result.getSuccess();
    }

    @RequestMapping("/site")
    public Result<Map<String, PluginSite>> openSite(@RequestParam String url) throws IOException {
        PluginSite site = PluginUtil.getSite(url);
        Map<String, PluginSite> result = new HashMap<>(1);
        result.put(url, site);
        return Result.getSuccess(result);
    }

    @RequestMapping("/disabled")
    public Result<String> disabled(@RequestParam String id) {
        pluginFactory.disablePlugin(id);
        return Result.getSuccess();
    }

    @RequestMapping("/plugins")
    public Result<List<PluginConfig>> getPlugins() {
        return Result.getSuccess(pluginFactory.getPluginList());
    }
}
