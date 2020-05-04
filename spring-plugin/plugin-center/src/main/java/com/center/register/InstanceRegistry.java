package com.center.register;

import com.center.info.InstanceInfo;

import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/4 17:11
 */
public interface InstanceRegistry {

    /**
     * 注册插件服务
     * @param info
     */
    void register(InstanceInfo info);


    /**
     * 统一缓存应用配置
     * @param info
     */
    void cacheAppPluginConfigs(InstanceInfo info);

    /**
     * 获取已注册的服务
     * @return
     */
    List<InstanceInfo> getApplications();

    /**
     * 获取应用详细
     * @param name 应用名
     * @return
     */
    InstanceInfo getApplicationByName(String name);
}
