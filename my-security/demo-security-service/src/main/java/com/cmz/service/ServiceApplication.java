package com.cmz.service;

import com.alibaba.fastjson.JSONObject;
import com.cmz.sec.annotation.EnableMySecurityWeb;
import com.cmz.sec.annotation.HasRole;
import com.cmz.sec.annotation.SecurityUser;
import com.cmz.sec.jwt.JwtUtil;
import com.cmz.sec.userdetails.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 11:55
 */
@SpringBootApplication
@RestController
public class ServiceApplication {

    private Logger logger = LoggerFactory.getLogger(ServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @PostMapping("/service")
    public String test(@SecurityUser UserDetails user) {
        logger.info("service user= {}", user);
        return JSONObject.toJSONString(user);
    }

}
