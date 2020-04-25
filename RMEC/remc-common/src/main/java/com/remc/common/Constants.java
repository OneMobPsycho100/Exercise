package com.remc.common;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/18 15:11
 */
public class Constants {

    public static final String ROUTERKEY_ORDER = "order";
    public static final String ROUTERKEY_STOCK = "STOCK";
    public static final String QUEUE_STOCK = "remc.stock";
    public static final String QUEUE_ORDER = "remc.order";

    public static final String QUEUE_TASK_DELAY = "delay";
    public static final String ROUTERKEY_TASK_DELAY = "remc.delay";

    private Constants() {
    }

    public static final class BizType {
        public static final String TYPE_ORDER_STOCK = "order_stock";
    }

    public static final class MessageStatus {
        public static final int STATUS_INIT = 1;
        public static final int STATUS_SEND = 2;
        public static final int STATUS_END = 3;
    }
}
