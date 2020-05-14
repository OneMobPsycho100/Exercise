package com.cmz.sec.userdetails;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/12 21:42
 */
public class UserDetails implements Serializable {

    private String userId;
    private String password;
    private String username;
    private List<String> roles;
    private String token;


    public UserDetails() {
    }

    public UserDetails(String userId, String password, String username, List<String> roles) {
//        if (username == null || "".equals(username) || password == null) {
//            throw new IllegalArgumentException(
//                    "Cannot pass null or empty values to constructor");
//        }

        this.userId = userId;
        this.password = password;
        this.username = username;
        this.roles = roles;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                ", token='" + token + '\'' +
                '}';
    }
}
