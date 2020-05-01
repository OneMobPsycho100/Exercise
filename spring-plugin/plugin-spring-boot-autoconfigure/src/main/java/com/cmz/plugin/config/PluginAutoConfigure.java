package com.cmz.plugin.config;

import com.cmz.plugin.constant.Constants;
import com.cmz.plugin.controller.SpringPluginManagerController;
import com.cmz.plugin.factory.SpringPluginFactory;
import com.cmz.plugin.factory.impl.DefaultSpringPluginFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/29 22:28
 */
@Configuration
@ConditionalOnProperty(prefix = Constants.PLUGIN_ACTIVE, value = {"enabled"}, matchIfMissing = true)
@Import(AspectAutoConfigure.class)
public class PluginAutoConfigure {

    @Bean
    public SpringPluginFactory springPluginFactory() { return new DefaultSpringPluginFactory(); }

    @Bean
    public SpringPluginManagerController managerController() {
        return new SpringPluginManagerController();
    }

}
