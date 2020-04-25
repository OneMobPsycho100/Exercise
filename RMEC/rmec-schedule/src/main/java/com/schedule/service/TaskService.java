package com.schedule.service;

import com.schedule.entity.Task;
import com.schedule.mapper.TaskMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenmingzhe
 * @since 2020-04-23
 */
@Service
public class TaskService extends  ServiceImpl<TaskMapper, Task> {

}
