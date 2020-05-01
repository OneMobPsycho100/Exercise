package com.cmz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/30 12:00
 */
@SpringBootApplication
@ComponentScan(value = {"com.cmz.service"})
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
