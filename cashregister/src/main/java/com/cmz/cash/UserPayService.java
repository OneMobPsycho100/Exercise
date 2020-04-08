package com.cmz.cash;

import java.math.BigDecimal;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/8 21:12
 */
public interface UserPayService {

    /**
     * 计算实际应付价格
     * @param price
     * @return
     */
    BigDecimal pay(BigDecimal price);

}
