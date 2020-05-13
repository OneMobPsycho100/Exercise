package com.cmz.sec.annotation.support.resolver;

import com.cmz.sec.annotation.SecurityUser;
import com.cmz.sec.exception.MySecurityException;
import com.cmz.sec.jwt.JwtUtil;
import com.cmz.sec.userdetails.UserDetails;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 拦截带有 {@link SecurityUser}注解的方法 将用户的信息由token取出注入到方法参数里
 *
 * @Author: chenmingzhe
 * @Date: 2020/5/13 14:30
 */
public class MyHandlerMethodResolverComposite implements HandlerMethodArgumentResolver {

    private JwtUtil jwtUtil;

    public MyHandlerMethodResolverComposite(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SecurityUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        UserDetails userDetails = null;
        String token = jwtUtil.getTokenFromRequest(request);
        if (token != null) {
            String className = parameter.getParameterType().getName();
            if (!UserDetails.class.getName().equals(className)) {
                throw new MySecurityException(String
                        .format("@SecurityUser cannot be used on class=%s", className));
            }
            userDetails = jwtUtil.getUserFromToken(token);
        } else {
            throw new MySecurityException("Missing valid token cannot be injected into user");
        }
        return userDetails;
    }

}
