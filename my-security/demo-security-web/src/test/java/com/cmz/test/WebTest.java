package com.cmz.test;

import com.alibaba.fastjson.JSONObject;
import com.cmz.sec.userdetails.UserDetails;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chenmingzhe
 * @Date: 2020/5/14 13:37
 */
public class WebTest extends BaseWebJUnit4Test {

    Logger logger = LoggerFactory.getLogger(WebTest.class);

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testLoginForm() {
        String token = restTemplate
                .postForObject("http://127.0.0.1:8100/login?username=user&password=123",
                        null, String.class);
        logger.info("token= {}", token);
    }

    @Test
    public void testLoginBody() {
        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.add("Authorization",
//                "Bearer " + MySecurityContextHolder.getUserContext().getToken());
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        UserDetails user = new UserDetails();
        user.setUsername("user");
        user.setPassword("123");
        requestBody.add("user", JSONObject.toJSONString(user));
        HttpEntity<MultiValueMap> requestEntity =
                new HttpEntity<>(requestBody, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity("http://127.0.0.1:8100/login",
                        requestEntity, String.class);
        logger.info("token= {}", responseEntity.getBody());
    }

    @Test
    public void  testLoginCookie() {
        UserDetails user = new UserDetails();
        user.setUsername("user");
        user.setPassword("123");
        List<String> cookies = new ArrayList<>();
        cookies.add("user=" + JSONObject.toJSONString(user));
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.put(HttpHeaders.COOKIE, cookies);
//        requestHeaders.add("Authorization",
//                "Bearer " + MySecurityContextHolder.getUserContext().getToken());
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap> requestEntity =
                new HttpEntity<>(requestBody, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity("http://127.0.0.1:8100/login",
                        requestEntity, String.class);
        logger.info("token= {}", responseEntity.getBody());
    }

    @Test
    public void testHasRole() {
        HttpHeaders requestHeaders = new HttpHeaders();
       requestHeaders.add("Authorization",
                "Bearer eyJhbGciOiJIUzM4NCJ9.eyJpZCI6IjEiLCJ1c2VybmFtZSI6InVzZXIiLCJyb2xlcyI6WyJVU0VSIl0sImlhdCI6MTU4OTQzNjE5NCwiZXhwIjoxNTkwNjQ1Nzk0fQ.em3KbeLEvXT1e1oQOVqsMXS_uO1BxMTU5YvY4E8yiZ29b4JYFDqCgRbRQdOnd6-L");
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap> requestEntity =
                new HttpEntity<>(requestBody, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity("http://127.0.0.1:8100/test",
                        requestEntity, String.class);
        logger.info("info= {}", responseEntity.getBody());
    }

    @Test
    public void testSecurityUser() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization",
                "Bearer eyJhbGciOiJIUzM4NCJ9.eyJpZCI6IjIiLCJ1c2VybmFtZSI6ImFkbWluIiwicm9sZXMiOlsiQURNSU4iXSwiaWF0IjoxNTg5NDI3ODU2LCJleHAiOjE1OTA2Mzc0NTZ9.Ta1ktQ5ZCy-RgGHTR60wicd38oR3_ZvYbY1Z_td4WiO4qU5I_8MiqxYsIzLgwXVN");
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap> requestEntity =
                new HttpEntity<>(requestBody, requestHeaders);
        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity("http://127.0.0.1:8100/test",
                        requestEntity, String.class);
        logger.info("info= {}", responseEntity.getBody());
    }
}
