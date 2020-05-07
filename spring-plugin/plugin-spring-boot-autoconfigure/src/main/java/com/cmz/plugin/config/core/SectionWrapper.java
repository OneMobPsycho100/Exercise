package com.cmz.plugin.config.core;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import com.cmz.plugin.annotation.SetAspect;

/**
 * 为标识为{@link SetAspect}目标Bean创建AspectJ代理
 *
 * @Author: chenmingzhe
 * @Date: 2020/5/7 21:54
 */
public class SectionWrapper<T> implements FactoryBean<T> {

    private Class<T> proxyBeanAspect;

    public SectionWrapper() {
    }

    public SectionWrapper(Class<T> proxyBeanAspect) {
        this.proxyBeanAspect = proxyBeanAspect;
    }

    @Override
    public T getObject() throws Exception {
        T needProxyBean = proxyBeanAspect.newInstance();
        AspectJProxyFactory factory = new AspectJProxyFactory(needProxyBean);
        return factory.getProxy();
    }

    @Override
    public Class<?> getObjectType() {
        return proxyBeanAspect;
    }
}
