package com.cmz.sec.web.service;

import com.cmz.sec.userdetails.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 21:52
 */
public class UserLoginService {

    private Logger logger = LoggerFactory.getLogger(UserLoginService.class);

    public String verify(String username, String password) {
        logger.info("username={},password={}", username, password);
        return null;
    }

    public String verify(UserDetails user) {
        logger.info("user= {}", user);
        return null;
    }

    public String verify(String userStr) {
        logger.info("userStr= {}", userStr);
        return null;
    }
}
