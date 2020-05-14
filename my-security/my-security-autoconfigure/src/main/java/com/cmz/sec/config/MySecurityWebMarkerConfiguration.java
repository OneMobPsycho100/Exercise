package com.cmz.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始标记
 * @Author: chenmingzhe
 * @Date: 2020/5/13 11:20
 */
@Configuration(proxyBeanMethods = false)
public class MySecurityWebMarkerConfiguration {
    public MySecurityWebMarkerConfiguration() {
    }

    @Bean
    public MySecurityWebMarkerConfiguration.MyMarker mySecurityWebMarkerBean() {
        return new MySecurityWebMarkerConfiguration.MyMarker();
    }

    class MyMarker {
        MyMarker() {
        }
    }
}
