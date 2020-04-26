package com.stock.controller;

import com.remc.common.Constants;
import com.remc.common.Result;
import com.remc.dto.MessageDto;
import com.remc.service.RabbitMQService;
import com.stock.api.StockServiceApi;
import com.stock.entity.StockLog;
import com.stock.service.StockLogService;
import com.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StockController implements StockServiceApi {

    @Autowired
    private StockLogService stockLogService;
    @Autowired
    private RabbitMQService rabbitMQService;

    @Override
    public Result<String> queryStockStatus(String bizId) {
        StockLog stockLog = stockLogService
                .lambdaQuery().eq(StockLog::getBizid, bizId).one();
        // 未扣减成功，重新发送消息
        if (stockLog == null) {
            MessageDto messageDto = new MessageDto();
            messageDto.setBizid(bizId);
            messageDto.setBiztype(Constants.BizType.TYPE_ORDER_STOCK);
            messageDto.setStatus(Constants.MessageStatus.STATUS_SEND);
            messageDto.setCreatetime(new Date());
            rabbitMQService.sendMessage(Constants.ROUTERKEY_STOCK, messageDto);
        }
        return Result.getSuccess();
    }
}

