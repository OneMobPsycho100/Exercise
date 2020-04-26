package com.schedule.api;

import com.remc.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/23 21:30
 */
@RequestMapping("/schedule-task")
public interface ScheduleTaskApi {

    /**
     * 创建延时任务
     *
     * @param bizId
     * @param taskName
     * @return
     */
    @RequestMapping("/createDelayTask")
    Result<String> createDelayTask(@RequestParam String bizId, @RequestParam String taskName);
}
