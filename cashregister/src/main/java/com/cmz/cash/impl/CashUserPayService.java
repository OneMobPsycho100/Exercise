package com.cmz.cash.impl;

import com.cmz.cash.DiscountEnum;
import com.cmz.cash.UserPayService;

import java.math.BigDecimal;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/8 21:31
 */
public class CashUserPayService implements UserPayService {

    @Override
    public BigDecimal pay(BigDecimal price) {
        //TODO 做一些其它事情
        return Calculation.calculate(DiscountEnum
                .getDiscountByPayType(Calculation.Constants.TYPE_CASH), price);
    }
}
