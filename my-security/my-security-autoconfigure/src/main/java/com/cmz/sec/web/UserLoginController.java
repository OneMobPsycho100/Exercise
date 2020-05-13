package com.cmz.sec.web;

import com.cmz.sec.userdetails.UserDetails;
import com.cmz.sec.web.service.UserLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 21:38
 */
@RestController
@RequestMapping("/sec")
public class UserLoginController {

    @Autowired
    private UserLoginService loginService;

    @PostMapping("/formLogin")
    public String formLogin(@RequestParam String username, @RequestParam String password) {
        String token = loginService.verify(username, password);
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        return "账号或密码错误！";
    }

    @PostMapping("/jsonLogin")
    public String jsonLogin(@RequestBody UserDetails user) {
        String token = loginService.verify(user);
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        return "账号或密码错误！";
    }

    @PostMapping("/cookieLogin")
    public String cookieLogin(@CookieValue String userStr) {
        String token = loginService.verify(userStr);
        if (StringUtils.isNotBlank(token)) {
            return token;
        }
        return "账号或密码错误！";
    }
}
