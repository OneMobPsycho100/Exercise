package com.center.register;

import com.center.info.InstanceInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/4 17:45
 */
public class InstanceRegistryImpl implements InstanceRegistry {

    private static final Map<String, Map<String, InstanceInfo>> APP_REGISTER_ALL = new ConcurrentHashMap<>();
    private static final Map<String, InstanceInfo> APP_REGISTER_SINGLE = new ConcurrentHashMap<>();
    private Lock read;
    private Lock write;

    public InstanceRegistryImpl() {
        ReentrantReadWriteLock reentrantLock = new ReentrantReadWriteLock();
        this.read = reentrantLock.readLock();
        this.write = reentrantLock.writeLock();
    }

    @Override
    public void register(InstanceInfo info) {
        try {
            read.lock();
            String appName = info.getAppName();
            String ipAddr = info.getIpAddr();
            Map<String, InstanceInfo> infoMap = APP_REGISTER_ALL.get(appName);
            if (infoMap != null) {
                if (!infoMap.containsKey(ipAddr)) {
                    infoMap.put(ipAddr, info);
                    APP_REGISTER_ALL.putIfAbsent(appName, infoMap);
                }
            } else {
                Map<String, InstanceInfo> instanceMap = new ConcurrentHashMap<>();
                instanceMap.put(ipAddr, info);
                APP_REGISTER_ALL.putIfAbsent(appName, instanceMap);
            }
        } finally {
            read.unlock();
        }
    }

    @Override
    public void cacheAppPluginConfigs(InstanceInfo info) {
        try {
            read.lock();
            APP_REGISTER_SINGLE.put(info.getAppName(), info);
        } finally {
            read.unlock();
        }
    }

    @Override
    public List<InstanceInfo> getApplications() {
        try {
            write.lock();
            return (ArrayList<InstanceInfo>) APP_REGISTER_SINGLE.values();
        } finally {
            write.unlock();
        }
    }

    @Override
    public InstanceInfo getApplicationByName(String name) {
        return APP_REGISTER_SINGLE.get(name);
    }


}
