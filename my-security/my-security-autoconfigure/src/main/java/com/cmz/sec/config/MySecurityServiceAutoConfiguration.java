package com.cmz.sec.config;

import com.cmz.sec.annotation.support.RoleAspect;
import com.cmz.sec.annotation.support.resolver.MyHandlerMethodResolverComposite;
import com.cmz.sec.interceptor.MySecurityInterceptor;
import com.cmz.sec.interceptor.UserAuthRestInterceptor;
import com.cmz.sec.jwt.JwtUtil;
import com.cmz.sec.properties.MySecurityProperties;
import com.cmz.sec.web.UserLoginController;
import com.cmz.sec.web.service.UserLoginService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 11:19
 */
@Configuration
@EnableConfigurationProperties(MySecurityProperties.class)
public class MySecurityServiceAutoConfiguration implements WebMvcConfigurer {

    private MySecurityProperties properties;
    private JwtUtil jwtUtil;

    public MySecurityServiceAutoConfiguration(MySecurityProperties properties) {
        this.properties = properties;
        this.jwtUtil = new JwtUtil(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtUtil jwtUtil() {
        return this.jwtUtil;
    }

    @Bean
    @ConditionalOnMissingBean
    public RoleAspect roleAspect() {
        return new RoleAspect();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAuthRestInterceptor(jwtUtil))
                .excludePathPatterns(properties.getWhitelist());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MyHandlerMethodResolverComposite(jwtUtil));
    }

}
