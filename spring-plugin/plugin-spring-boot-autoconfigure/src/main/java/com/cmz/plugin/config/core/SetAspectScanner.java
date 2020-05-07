package com.cmz.plugin.config.core;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/7 22:21
 */
public class SetAspectScanner extends ClassPathBeanDefinitionScanner {

    public SetAspectScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

}
