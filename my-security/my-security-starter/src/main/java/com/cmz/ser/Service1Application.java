package com.cmz.ser;

import com.cmz.sec.annotation.EnableMySecurityWeb;
import com.cmz.sec.annotation.HasRole;
import com.cmz.sec.annotation.SecurityUser;
import com.cmz.sec.jwt.JwtUtil;
import com.cmz.sec.userdetails.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 11:55
 */
@SpringBootApplication
@EnableMySecurityWeb
@RestController
public class Service1Application {

    private Logger logger = LoggerFactory.getLogger(Service1Application.class);

    @Autowired
    private JwtUtil jwtUtil;

    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }

    @PostMapping("/test")
    //@HasRole("ADMIN")
    public void test(@SecurityUser UserDetails user) {
        logger.info("user= {}", user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDetails user) {
        String token = jwtUtil.generateToken(user);
        logger.info("token= {}", token);
        return token;
    }

}
