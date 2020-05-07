package com.cmz.plugin.config;

import com.cmz.plugin.constant.Constants;
import com.cmz.plugin.controller.SpringPluginManagerController;
import com.cmz.plugin.factory.SpringPluginFactory;
import com.cmz.plugin.factory.impl.DefaultSpringPluginFactory;
import org.aopalliance.intercept.Joinpoint;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/29 22:28
 */
@Configuration
@ConditionalOnProperty(
        prefix = "plugin.aop",
        name = {"active"},
        havingValue = "enabled",
        matchIfMissing = true
)
public class PluginAutoConfigure implements EnvironmentAware {
    private Environment environment;

    public PluginAutoConfigure() {
    }

    @ConditionalOnProperty(
            prefix = "plugin.aop",
            name = {"pointcut"},
            matchIfMissing = false
    )
    @Bean
    public AspectJExpressionPointcutAdvisor pluginPointcutAdvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(this.environment.getProperty("plugin.aop.pointcut"));
        advisor.setAdvice((MethodInterceptor) Joinpoint::proceed);
        return advisor;
    }

    @Bean
    public SpringPluginFactory springPluginFactory() {
        return new DefaultSpringPluginFactory();
    }

    @Bean
    public SpringPluginManagerController managerController() {
        return new SpringPluginManagerController();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
