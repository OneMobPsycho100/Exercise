package com.plugin.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/30 11:28
 */
public class ServerLogPlugin implements MethodBeforeAdvice {

    private Logger logger = LoggerFactory.getLogger(ServerLogPlugin.class);

    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("ServerLogPlugin--------------");
        logger.info("{}.{}() 参数:{}", method.getDeclaringClass().getName()
                , method.getName(), Arrays.toString(args));
    }
}