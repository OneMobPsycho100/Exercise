package com.cmz.web;

import com.alibaba.fastjson.JSONObject;
import com.cmz.sec.annotation.EnableMySecurityWeb;
import com.cmz.sec.annotation.HasRole;
import com.cmz.sec.annotation.SecurityUser;
import com.cmz.sec.context.MySecurityContextHolder;
import com.cmz.sec.jwt.JwtUtil;
import com.cmz.sec.userdetails.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/13 11:55
 */
@SpringBootApplication
@EnableMySecurityWeb
@RestController
public class WebApplication {

    private Logger logger = LoggerFactory.getLogger(WebApplication.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @PostMapping("/test")
    @HasRole("ADMIN")
    public String test(@SecurityUser UserDetails user) {
        // 分布式环境下需要将token放入header中传递
        // 这里只是使用restTemplate简单演示，后续可以通过feign拦截器可以统一添加header

        logger.info("user= {}", user);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization",
                "Bearer " + MySecurityContextHolder.getUserContext().getToken());
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("user", JSONObject.toJSONString(user));
        HttpEntity<MultiValueMap> requestEntity =
                new HttpEntity<>(requestBody, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity("http://127.0.0.1:8101/service", requestEntity, String.class);
        logger.info("result user= {}", JSONObject.parseObject(responseEntity.getBody()));

        return JSONObject.toJSONString(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDetails user) {
        String token = jwtUtil.generateToken(user);
        logger.info("token= {}", token);
        return token;
    }

}
