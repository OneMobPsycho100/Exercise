package com.cmz.sec.web.service;

import com.cmz.sec.context.MySecurityContextHolder;
import com.cmz.sec.jwt.JwtUtil;
import com.cmz.sec.userdetails.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 21:52
 */
public class UserLoginService {

    private Logger logger = LoggerFactory.getLogger(UserLoginService.class);

    private final static Map<String, UserDetails> DEFAULT_USERS = new HashMap<>();

    static {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        UserDetails user = new UserDetails("1", "123", "user", roles);
        DEFAULT_USERS.put("user", user);

        List<String> rolesAdmin = new ArrayList<>();
        rolesAdmin.add("ADMIN");
        UserDetails userAdmin = new UserDetails("2", "123", "admin", rolesAdmin);
        DEFAULT_USERS.put("admin", userAdmin);
    }

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        if (DEFAULT_USERS.containsKey(username)) {
            UserDetails user = DEFAULT_USERS.get(username);
            if (user.getPassword().equals(password)) {
                MySecurityContextHolder.setUserContext(user);
                String token = jwtUtil.generateToken(user);
                logger.info("token= {}", token);
                return token;
            }
        }
        return null;
    }
}
