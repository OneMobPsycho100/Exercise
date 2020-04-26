package com.schedule.api;

import com.order.api.OrderServiceApi;
import com.stock.api.StockServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/26 16:54
 */
@FeignClient(name = "${application.service.stock.name}", contextId = "StockApiService")
public interface StockApiService extends StockServiceApi {
}
