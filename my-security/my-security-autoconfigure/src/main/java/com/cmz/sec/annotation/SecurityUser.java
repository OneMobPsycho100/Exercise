package com.cmz.sec.annotation;

import java.lang.annotation.*;

import com.cmz.sec.annotation.support.resolver.MyHandlerMethodResolverComposite;

/**
 * 登录用户信息参数注入 {@link MyHandlerMethodResolverComposite}
 *
 * @Author: chenmingzhe
 * @Date: 2020/5/13 13:21
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SecurityUser {

    String value() default "";
}
