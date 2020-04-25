package com.schedule;

import com.remc.config.RmecConfiguration;
import com.schedule.common.Constants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/14 23:25
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(Constants.SCAN_MAPPER)
@Import(RmecConfiguration.class)
public class ScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, args);
    }

}
