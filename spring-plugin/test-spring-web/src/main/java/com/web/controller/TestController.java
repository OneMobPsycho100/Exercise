package com.web.controller;

import com.cmz.plugin.module.Result;
import com.web.module.User;
import com.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/30 14:37
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public Result<User> getUser(@RequestParam String name) {
        return Result.getSuccess(userService.getUserByName(name));
    }

}
