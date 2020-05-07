package com.cmz.plugin.config.core;

import com.cmz.plugin.annotation.SetAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.Set;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/7 22:16
 */
public class SetAspectRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes aspectScansAttrs = AnnotationAttributes.fromMap(importingClassMetadata
                .getAnnotationAttributes(SetAspect.class.getName()));
        if (aspectScansAttrs != null) {
            this.doScanner(aspectScansAttrs, registry);
        }
    }

    private void doScanner(AnnotationAttributes aspectScansAttrs, BeanDefinitionRegistry registry) {
        String[] packages = aspectScansAttrs.getStringArray("basePackage");
        SetAspectScanner scanner = new SetAspectScanner(registry);
        scanner.doScan(packages);
    }

    static class SetAspectScanner extends ClassPathBeanDefinitionScanner {

        private Logger logger = LoggerFactory.getLogger(SetAspectScanner.class);

        SetAspectScanner(BeanDefinitionRegistry registry) {
            super(registry);
        }

        @Override
        public Set<BeanDefinitionHolder> doScan(String... basePackages) {
            Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
            if (beanDefinitions.isEmpty()) {
                this.logger.warn("no annotation found @SetAspect {}", Arrays.toString(basePackages));
            } else {

            }
            return beanDefinitions;
        }

    }
}
