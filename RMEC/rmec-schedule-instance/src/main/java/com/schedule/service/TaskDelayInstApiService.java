package com.schedule.service;

import com.schedule.api.ScheduleTaskApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/25 19:21
 */
@Service
public interface TaskDelayInstApiService extends ScheduleTaskApi {
}
