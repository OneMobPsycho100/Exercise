package com.center.info;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/4 17:22
 */
public class PluginInfo {

    private String pluginId;
    private String name;
    private String className;
    private String jarRemoteUrl;
    private String version;

    public String getPluginId() {
        return pluginId;
    }

    public void setPluginId(String pluginId) {
        this.pluginId = pluginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJarRemoteUrl() {
        return jarRemoteUrl;
    }

    public void setJarRemoteUrl(String jarRemoteUrl) {
        this.jarRemoteUrl = jarRemoteUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
