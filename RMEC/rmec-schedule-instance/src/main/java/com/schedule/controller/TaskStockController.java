package com.schedule.controller;

import com.remc.common.Result;
import com.schedule.api.StockApiService;
import com.schedule.api.StockTaskApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/26 21:49
 */
@RestController
public class TaskStockController implements StockTaskApi {

    @Autowired
    private StockApiService stockApiService;

    @Override
    public Result<String> queryStockInfo(String bizId) {
        return stockApiService.queryStockStatus(bizId);
    }
}
