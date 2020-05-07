package com.web;

import com.web.service.UserService;
import com.web.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/30 12:00
 */
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

//    @Bean
//    public TestController testController() {
//        return new TestController();
//    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}
