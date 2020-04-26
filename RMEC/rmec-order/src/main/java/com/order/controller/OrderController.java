package com.order.controller;

import com.order.entity.Order;
import com.order.service.OrderService;
import com.remc.common.Constants;
import com.remc.common.IdWorker;
import com.remc.common.Result;
import com.remc.dto.MessageDto;
import com.remc.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private RabbitMQService rabbitMqService;
    @Autowired
    private IdWorker idWorker;

    @GetMapping("generateOrders")
    public Result order() {
        String orderId = idWorker.nextId();
        rabbitMqService.sendMessage(RabbitMQService.ROUTERKEY_ORDER,
                new MessageDto(orderId,Constants.BizType.TYPE_ORDER_STOCK
                        ,Constants.MessageStatus.STATUS_INIT,null,new Date())
//                MessageDto.newBuilder()
//                        .bizid(orderId)
//                        .biztype(Constants.BizType.TYPE_ORDER_STOCK)
//                        .status(Constants.MessageStatus.STATUS_INIT)
//                        .createtime(new Date())
        );

        Order order = orderService.generateOrder(orderId);

        rabbitMqService.sendMessage(RabbitMQService.ROUTERKEY_ORDER,
//              MessageDto.newBuilder()
//                        .bizid(orderId)
//                        .biztype(Constants.BizType.TYPE_ORDER_STOCK)
//                        .status(Constants.MessageStatus.STATUS_SEND)
//                        .data(order)
//                        .createtime(new Date())
                new MessageDto(orderId,Constants.BizType.TYPE_ORDER_STOCK
                        ,Constants.MessageStatus.STATUS_SEND,order,new Date())
        );
        return Result.getSuccess();
    }

}

