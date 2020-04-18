package com.order.config;

import com.remc.service.RabbitMqService;
import com.remc.config.RmecConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/18 22:58
 */
@Configuration
@Import({RmecConfiguration.class, RabbitMqService.class})
public class RabbitMqConfig {

}
