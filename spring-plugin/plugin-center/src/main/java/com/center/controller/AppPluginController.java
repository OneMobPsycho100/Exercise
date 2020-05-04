package com.center.controller;

import com.center.info.InstanceInfo;
import com.center.response.Result;
import com.center.service.AppPluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/4 22:05
 */
@RestController
@RequestMapping("/appPlugin")
public class AppPluginController {

    @Autowired
    private AppPluginService appPluginService;

    @GetMapping("/findAllApp")
    public Result<List<InstanceInfo>> findAllApplications() {
        return Result.getSuccess(appPluginService.findAllApps());
    }

    @GetMapping("/findAppByName")
    public Result<InstanceInfo> findAppByName(@RequestParam String name) {
        return Result.getSuccess(appPluginService.findAppByName(name));
    }

    public Result<String> install(@RequestParam String appName, @RequestParam String pluginId) {

        return Result.getSuccess();
    }
}
