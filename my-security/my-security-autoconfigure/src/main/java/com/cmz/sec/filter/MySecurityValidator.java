package com.cmz.sec.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 8:59
 */
public interface MySecurityValidator {

    /**
     * 权限验证
     * @param request
     * @param response
     * @return
     */
    boolean doValidator(ServletRequest request, ServletResponse response);
}
