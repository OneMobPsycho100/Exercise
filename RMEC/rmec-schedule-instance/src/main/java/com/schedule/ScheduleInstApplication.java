package com.schedule;

import com.remc.common.Constants;
import com.schedule.annotation.EnableSchedule;
import com.schedule.common.ScheduleConstants;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/14 23:25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSchedule
@MapperScan(Constants.SCAN_MAPPER)
@EnableFeignClients
public class ScheduleInstApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleInstApplication.class, args);
    }

}
