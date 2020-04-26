package com.stock.service;

import com.stock.entity.StockLog;
import com.stock.mapper.StockLogMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 库存扣减LOG 服务类
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-26
 */
@Service
public class StockLogService extends  ServiceImpl<StockLogMapper, StockLog> {

}
