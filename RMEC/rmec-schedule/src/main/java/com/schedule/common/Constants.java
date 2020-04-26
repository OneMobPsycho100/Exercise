package com.schedule.common;

import java.util.UUID;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/18 15:11
 */
public class Constants {

    /**
     * enable
     */
    public static final String TASK_STATUS_ON = "1";

    /**
     * disable
     */
    public static final String TASK_STATUS_OFF = "0";

    /**
     * 任务触发器key
     */
    public static final String DELAY_JOB_TRIGGER_KEY = UUID.randomUUID().toString();
    public static final String DELAY_JOB_KEY = UUID.randomUUID().toString();

    public static final String DELAY_JOB_GROUP = "delay";


    private Constants() {
    }

}
