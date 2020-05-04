package com.center.info;

import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/4 17:14
 */
public class InstanceInfo {

    private String ipAddr;
    private String port;
    private String appName;
    private List<InstancePluginInfo> appPlugins;

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<InstancePluginInfo> getAppPlugins() {
        return appPlugins;
    }

    public void setAppPlugins(List<InstancePluginInfo> appPlugins) {
        this.appPlugins = appPlugins;
    }
}
