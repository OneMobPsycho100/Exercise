package com.cmz.sec.userdetails;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/12 21:42
 */
public class UserDetails implements Serializable {

    private String userId;
    private String password;
    private String username;
    private Set<String> authorities;


    public UserDetails() {
    }

    public UserDetails(String userId, String password, String username, Set<String> authorities) {
//        if (username == null || "".equals(username) || password == null) {
//            throw new IllegalArgumentException(
//                    "Cannot pass null or empty values to constructor");
//        }

        this.userId = userId;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
