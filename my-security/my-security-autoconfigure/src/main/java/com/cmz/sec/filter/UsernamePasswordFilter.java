package com.cmz.sec.filter;

import com.cmz.sec.exception.MySecurityException;
import com.cmz.sec.userdetails.UserDetailService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 9:50
 */
public class UsernamePasswordFilter implements Filter, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDetailService userDetailService = applicationContext.getBean(UserDetailService.class);
        if (userDetailService == null) {
            throw new MySecurityException("userDetailService implementation was not found");
        }
        userDetailService.loadUserByUsername(username);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
