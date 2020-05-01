package com.cmz.plugin.module;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/29 16:51
 */
public class PluginSite implements Serializable {

    /**
     * 站点名称
     */
    private String name;

    private List<PluginConfig> configs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PluginConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<PluginConfig> configs) {
        this.configs = configs;
    }
}
