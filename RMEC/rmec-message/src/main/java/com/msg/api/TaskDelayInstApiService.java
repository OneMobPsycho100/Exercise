package com.msg.api;

import com.schedule.api.ScheduleTaskApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/25 19:21
 */
@FeignClient(name = "${application.service.schedule.name}", contextId = "TaskDelayInstApiService")
public interface TaskDelayInstApiService extends ScheduleTaskApi {
}
