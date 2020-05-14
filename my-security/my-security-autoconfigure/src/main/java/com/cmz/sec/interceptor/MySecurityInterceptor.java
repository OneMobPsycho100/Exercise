package com.cmz.sec.interceptor;

import com.cmz.sec.context.MySecurityContextHolder;
import com.cmz.sec.exception.MySecurityException;
import com.cmz.sec.properties.MySecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 11:00
 */
public class MySecurityInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(MySecurityInterceptor.class);

    private MySecurityProperties securityProperties;

    public MySecurityInterceptor(MySecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AntPathMatcher matcher = new AntPathMatcher();
        String path = this.getRequestPath(request);
        securityProperties
                .getWhitelist()
                .stream()
                .filter(p -> matcher.match(p, path)
                        || MySecurityContextHolder.getUserContext() != null)
                .findFirst()
                .orElseThrow(() -> new MySecurityException("Permission denied"));

        logger.info("url: {}", path);

        if (matcher.match(securityProperties.getLoginUrl(), path)) {
            this.doLoginValidator(request, response);
            return false;
        }
        return true;
    }

    private void doLoginValidator(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            request.getRequestDispatcher("/sec/formLogin").forward(request, response);
            return;
        }
//        UserDetails user = JSONObject.parseObject(this.getBody(request), UserDetails.class);
//        if (user != null) {
//            request.getRequestDispatcher("/sec/jsonLogin").forward(request, response);
//            return;
//        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user".equals(cookie.getName())) {
                    request.getRequestDispatcher("/sec/cookieLogin").forward(request, response);
                    return;
                }
            }
        }
        //body
        request.getRequestDispatcher("/sec/jsonLogin").forward(request, response);
    }

    private String getRequestPath(HttpServletRequest request) {
        String url = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (pathInfo != null) {
            url = StringUtils.hasLength(url) ? url + pathInfo : pathInfo;
        }
        return url;
    }

    private String getBody(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String str;
        StringBuilder wholeStr = new StringBuilder();
        while ((str = br.readLine()) != null) {
            wholeStr.append(str);
        }
        return wholeStr.toString();
    }
}
