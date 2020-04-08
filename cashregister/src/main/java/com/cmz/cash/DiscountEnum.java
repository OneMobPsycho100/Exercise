package com.cmz.cash;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/8 21:35
 */
public enum DiscountEnum {

    /**
     * 支付方式
     */
    PHONE("400-100-7",1), CASH("500-100-8",2), CARD("600-100-8",3);

    private String discount;
    private int type;

    public String getDiscount() {
        return discount;
    }

    public int getType() {
        return type;
    }

    DiscountEnum(String discount, int type) {
        this.discount = discount;
        this.type = type;
    }

    public static String getDiscountByPayType(int type){
        for (DiscountEnum discounts : DiscountEnum.values()) {
            if (discounts.getType() == type) {
                return discounts.getDiscount();
            }
        }
        throw new IllegalArgumentException("this payment method is not supported");
    }
}
