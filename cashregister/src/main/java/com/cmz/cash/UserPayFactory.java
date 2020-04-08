package com.cmz.cash;

import com.cmz.cash.impl.Calculation;
import com.cmz.cash.impl.CardUserPayService;
import com.cmz.cash.impl.CashUserPayService;
import com.cmz.cash.impl.PhoneUserPayService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/8 22:22
 */
public class UserPayFactory {

    private static Map<Integer, UserPayService> service = new HashMap<>();

    static {
        service.put(Calculation.Constants.TYPE_PHONE, new PhoneUserPayService());
        service.put(Calculation.Constants.TYPE_CASH, new CashUserPayService());
        service.put(Calculation.Constants.TYPE_CARD, new CardUserPayService());
    }

    public static UserPayService getUserPayService(int type) {
        Optional.ofNullable(service.get(type))
                .orElseThrow(() -> new IllegalArgumentException("service not created"));
        return service.get(type);
    }
}
