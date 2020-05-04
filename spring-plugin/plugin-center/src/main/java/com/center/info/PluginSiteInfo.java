package com.center.info;

import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/4 17:26
 */
public class PluginSiteInfo {

    private String name;
    private List<PluginInfo> pluginInfos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PluginInfo> getPluginInfos() {
        return pluginInfos;
    }

    public void setPluginInfos(List<PluginInfo> pluginInfos) {
        this.pluginInfos = pluginInfos;
    }
}
