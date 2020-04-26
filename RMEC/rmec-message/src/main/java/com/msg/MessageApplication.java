package com.msg;

import com.remc.common.Constants;
import com.remc.config.RmecConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/14 23:25
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(Constants.SCAN_MAPPER)
@Import(RmecConfiguration.class)
@EnableFeignClients
public class MessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }
}
