package com.center.service.impl;

import com.center.info.InstanceInfo;
import com.center.register.InstanceRegistry;
import com.center.service.AppPluginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/4 22:10
 */
public class AppPluginServiceImpl implements AppPluginService {

    @Autowired
    private InstanceRegistry instanceRegistry;

    @Override
    public List<InstanceInfo> findAllApps() {
        return instanceRegistry.getApplications();
    }

    @Override
    public InstanceInfo findAppByName(String name) {
        return instanceRegistry.getApplicationByName(name);
    }
}
