package com.cmz.sec.annotation;

import java.lang.annotation.*;

import com.cmz.sec.annotation.support.RoleAspect;

/**
 * <p>权限判断注解，value值为权限名 切面{@link RoleAspect}
 * 会拦截带有该注解的方法</p>
 *
 * @Author: chenmingzhe
 * @Date: 2020/5/12 22:44
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasRole {
    String value();
}
