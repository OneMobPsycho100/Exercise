package com.cmz.test;

import com.cmz.cash.UserPayFactory;
import com.cmz.cash.UserPayService;
import com.cmz.cash.impl.Calculation;

import java.math.BigDecimal;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/8 21:43
 */
public class Test {

    public static void main(String[] args) {
        UserPayService userPayService =
                UserPayFactory.getUserPayService(Calculation.Constants.TYPE_CARD);
        System.out.println(userPayService.pay(new BigDecimal(800)));
    }
}
