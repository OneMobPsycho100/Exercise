package com.cmz.sec.annotation.support;

import com.cmz.sec.annotation.HasRole;
import com.cmz.sec.context.MySecurityContextHolder;
import com.cmz.sec.exception.MySecurityException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/12 22:49
 */
@Aspect
public class RoleAspect {

    @Pointcut("@annotation(com.cmz.sec.annotation.HasRole)")
    public void roleIntercept() {
    }

    @Before("roleIntercept()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        HasRole annotation = signature.getMethod().getAnnotation(HasRole.class);
        String roleName = annotation.value();
        Assert.notNull(roleName, "RoleName must not null");
        Optional.ofNullable(MySecurityContextHolder
                .getUserContext())
                .orElseThrow(() -> new MySecurityException("Insufficient user rights"))
                .getAuthorities()
                .stream()
                .filter(r -> r.equals(roleName))
                .findFirst()
                .orElseThrow(() -> new MySecurityException("Insufficient user rights"));
    }
}
