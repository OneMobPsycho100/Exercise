package com.order;

import com.order.common.Constants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/14 23:25
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(Constants.SCAN_MAPPER)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
