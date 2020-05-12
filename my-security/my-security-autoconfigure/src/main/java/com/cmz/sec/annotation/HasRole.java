package com.cmz.sec.annotation;

import java.lang.annotation.*;

/**
 *<p>权限判断注解，value值为权限名 {@link com.cmz.sec.aspect.RoleAspect}
 * 切面会拦截带有该注解的方法</p>
 *
 * @Author: chenmingzhe
 * @Date: 2020/5/12 22:44
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasRole {
    String value();
}
