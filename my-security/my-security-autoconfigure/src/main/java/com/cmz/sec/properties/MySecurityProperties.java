package com.cmz.sec.properties;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 11:22
 */
@ConfigurationProperties(prefix = "my-security")
public class MySecurityProperties {

    /**
     * 路径白名单
     */
    private List<String> whitelist = new ArrayList<>();

    /**
     * 登录地址
     */
    private String loginUrl = "/login";

    /**
     * jwt 配置
     */
    private JwtProperties jwtProperties = new JwtProperties();


    public List<String> getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(List<String> whitelist) {
        this.whitelist = whitelist;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public JwtProperties getJwtProperties() {
        return jwtProperties;
    }

    public void setJwtProperties(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public static class JwtProperties {
        /**
         * 盐
         */
        private String secret = "mySecuritymySecuritymySecuritymySecuritymySecuritymySecuritymySecurity";

        /**
         * token有效时间s，默认3天
         */
        private Long expirationInSecond = 259200L;

        /**
         * 算法HS512
         */
        private SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public Long getExpirationInSecond() {
            return expirationInSecond;
        }

        public void setExpirationInSecond(Long expirationInSecond) {
            this.expirationInSecond = expirationInSecond;
        }

        public SignatureAlgorithm getAlgorithm() {
            return algorithm;
        }

        public void setAlgorithm(SignatureAlgorithm algorithm) {
            this.algorithm = algorithm;
        }
    }

}
