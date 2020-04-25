package com.schedule.api;

import com.remc.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/23 21:30
 */
@RequestMapping("/schedule-task")
public interface ScheduleTaskApi {

    /**
     *创建延时任务
     * @param bizId
     * @param taskName
     * @return
     */
    Result<String> createDelayTask(String bizId,String taskName);
}
