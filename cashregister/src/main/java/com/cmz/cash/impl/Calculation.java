package com.cmz.cash.impl;

import java.math.BigDecimal;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/8 22:12
 */
public class Calculation {

    public static BigDecimal calculate(String discounts, BigDecimal price) {
        String[] discountInfo = discounts.split("-");
        BigDecimal full = new BigDecimal(discountInfo[0]);
        BigDecimal reduction = new BigDecimal(discountInfo[1]);
        BigDecimal discount = new BigDecimal(discountInfo[2]);
        if (price.compareTo(full) > 0) {
            return price.subtract(reduction).multiply(discount)
                    .divide(new BigDecimal(10), BigDecimal.ROUND_HALF_UP);
        }
        return price;
    }

    public interface Constants {
        int TYPE_PHONE = 1;
        int TYPE_CASH = 2;
        int TYPE_CARD = 3;
    }
}
