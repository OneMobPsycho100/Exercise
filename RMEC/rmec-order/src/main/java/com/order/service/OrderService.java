package com.order.service;

import com.order.entity.Order;
import com.order.mapper.OrderMapper;
import com.remc.common.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-18
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    @Autowired
    private IdWorker idWorker;

    public Order generateOrder(String orderId) {
        Order order = Order.newBuilder()
                .orderid(orderId)
                .goodname("MEIZU17PLUS")
                .price(new BigDecimal("233"))
                .createtime(LocalDateTime.now())
                .updatetime(LocalDateTime.now())
                .build();
        this.save(order);
        return order;
    }
}
