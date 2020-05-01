package com.cmz.service.impl;

import com.cmz.module.User;
import com.cmz.service.UserService;

/**
 * @Author: chenmingzhe
 * @Date: 2020/4/30 11:58
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getUserByName(String name) {
        return new User(name, "man", 18);
    }
}
