package com.center.service;

import com.center.info.InstanceInfo;

import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/4 22:09
 */
public interface AppPluginService {


    /**
     * 查询所有的应用列表
     * @return List<InstanceInfo>
     */
    List<InstanceInfo> findAllApps();

    /**
     * 获取应用详细信息
     * @param name 应用名
     * @return InstanceInfo
     */
    InstanceInfo findAppByName(String name);
}
