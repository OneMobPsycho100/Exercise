package com.cmz.sec.filter;

import com.cmz.sec.context.MySecurityContextHolder;
import com.cmz.sec.exception.MySecurityException;
import com.cmz.sec.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 9:03
 */
public class MySecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        AntPathMatcher matcher = new AntPathMatcher();
        String requestPath = ((HttpServletRequest) request).getContextPath();
        if (!matcher.match("/login", requestPath)) {
            UserDetails userContext = MySecurityContextHolder.getUserContext();
            if (userContext == null) {
                throw new MySecurityException("You need to login");
            }
        }
        chain.doFilter(request, response);
    }
}
