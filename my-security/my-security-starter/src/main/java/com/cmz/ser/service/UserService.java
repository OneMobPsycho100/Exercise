package com.cmz.ser.service;

import com.cmz.sec.annotation.HasRole;
import org.springframework.stereotype.Service;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 18:04
 */
@Service
public class UserService {

    @HasRole("ADMIN")
    public void sayHello() {
        System.out.println("hello");
    }
}
