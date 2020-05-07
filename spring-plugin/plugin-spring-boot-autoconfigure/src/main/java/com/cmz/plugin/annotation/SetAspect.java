package com.cmz.plugin.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *标识为需要增强的切面类
 * @Author: chenmingzhe
 * @Date: 2020/5/7 21:40
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface SetAspect {
}
