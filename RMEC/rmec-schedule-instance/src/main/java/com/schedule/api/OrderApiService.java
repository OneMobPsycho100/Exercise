package com.schedule.api;

import com.order.api.OrderServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/25 16:54
 */
@FeignClient(name = "${application.service.order.name}", contextId = "OrderApiService")
public interface OrderApiService extends OrderServiceApi {
}
