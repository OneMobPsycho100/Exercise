package com.schedule.controller;

import com.remc.common.Result;
import com.schedule.api.ScheduleTaskApi;
import com.schedule.common.Constants;
import com.schedule.entity.DelayTask;
import com.schedule.entity.TaskDelayinst;
import com.schedule.service.DelayTaskService;
import com.schedule.service.TaskDelayinstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-23
 */
@RestController
@RequestMapping("/task")
public class TaskController implements ScheduleTaskApi {

    @Autowired
    private DelayTaskService delayTaskService;
    @Autowired
    private TaskDelayinstService taskDelayinstService;

    @Override
    public Result<String> createDelayTask(String bizId, String taskName) {
        DelayTask delayTask = delayTaskService
                .lambdaQuery().eq(DelayTask::getName, taskName).one();
        if (delayTask == null) {
            return Result.getFailure(taskName + " does not exist！");
        }
        if (Constants.TASK_STATUS_OFF.equals(delayTask.getStatus())) {
            return Result.getFailure(taskName + " is disabled");
        }
        LocalDateTime plannedTime = taskDelayinstService.saveDelayInst(bizId, delayTask);
        delayTaskService.addDelayTaskToJob(Date.from(plannedTime
                .atZone(ZoneId.systemDefault()).toInstant()),taskName);
        return Result.getSuccess();
    }
}

