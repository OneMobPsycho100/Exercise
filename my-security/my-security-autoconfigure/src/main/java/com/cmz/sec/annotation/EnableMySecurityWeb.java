package com.cmz.sec.annotation;

import com.cmz.sec.config.MySecurityWebMarkerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启web端权限验证,服务间的调用默认安全不需要认证
 * @Author: chenmingzhe
 * @Date: 2020/5/13 11:52
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MySecurityWebMarkerConfiguration.class})
public @interface EnableMySecurityWeb {
}
