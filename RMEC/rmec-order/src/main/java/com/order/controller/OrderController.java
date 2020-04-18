package com.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.order.entity.Order;
import com.order.service.OrderService;
import com.remc.common.IdWorker;
import com.remc.common.Result;
import com.remc.service.RabbitMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/order")
public class OrderController   {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RabbitMqService rabbitMqService;
    @Autowired
    private IdWorker idWorker;

    @PostMapping("generateOrders")
    public Result order() {
        String orderId = idWorker.nextId();
        rabbitMqService.sendMessage(RabbitMqService.QUEUE_ORDER, orderId);
        Order order = orderService.generateOrder(orderId);
        rabbitMqService.sendMessage(RabbitMqService.QUEUE_ORDER, JSONObject.toJSONString(order));
        return Result.getSuccess();
    }

}

