package com.cmz.sec.interceptor;

import com.cmz.sec.context.MySecurityContextHolder;
import com.cmz.sec.exception.MySecurityException;
import com.cmz.sec.jwt.JwtUtil;
import com.cmz.sec.userdetails.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取header中的token 用于维护{@link MySecurityContextHolder}中储存的用户信息
 *
 * @Author: chenmingzhe
 * @Date: 2020/5/13 14:22
 */
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(UserAuthRestInterceptor.class);

    private JwtUtil jwtUtil;

    public UserAuthRestInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = jwtUtil.getTokenFromRequest(request);
        if (StringUtils.isEmpty(token)) {
            throw new MySecurityException("No valid token");
        }
        UserDetails user = jwtUtil.getUserFromToken(token);
        user.setToken(token);
        MySecurityContextHolder.setUserContext(user);
        logger.info("setUserContext= {} ", user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MySecurityContextHolder.clearContext();
        super.afterCompletion(request, response, handler, ex);
    }
}