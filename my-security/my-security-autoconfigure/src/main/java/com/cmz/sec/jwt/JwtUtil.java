package com.cmz.sec.jwt;

import com.cmz.sec.exception.MySecurityException;
import com.cmz.sec.properties.MySecurityProperties;
import com.cmz.sec.userdetails.UserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * jwt
 * @Author: chenmingzhe
 * @Date: 2020/5/13 13:42
 */
public class JwtUtil {

    private Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public static final String USER_ID = "id";
    public static final String USERNAME = "username";
    public static final String ROLES = "roles";

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";


    private MySecurityProperties securityProperties;

    public JwtUtil(MySecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * 从token中获取claim
     *
     * @param token token
     * @return claim
     */
    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(securityProperties.getJwtProperties()
                            .getSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            logger.error("token parsing error");
            throw new MySecurityException("illegal token", e);
        }
    }

    /**
     * 获取token的过期时间
     *
     * @param token token
     * @return 过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 计算token的过期时间
     *
     * @return 过期时间
     */
    private Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + securityProperties
                .getJwtProperties().getExpirationInSecond() * 1000);
    }

    /**
     * 为指定用户生成token
     *
     * @param user 用户信息
     * @return token
     */
    public String generateToken(UserDetails user) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USER_ID, user.getUserId());
        claims.put(USERNAME, user.getUsername());
        claims.put(ROLES, user.getRoles());
        Date createdTime = new Date();
        Date expirationTime = this.getExpirationTime();
        byte[] keyBytes = this.securityProperties
                .getJwtProperties().getSecret().getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdTime)
                .setExpiration(expirationTime)
                .signWith(key)
                .compact();
    }

    /**
     * 从request中获取token
     *
     * @param request 请求
     * @return token
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.isEmpty(header)) {
            throw new MySecurityException("没有找到名为Authorization的header");
        }
        if (!header.startsWith(BEARER)) {
            throw new MySecurityException("token必须以'Bearer '开头");
        }
        return header.substring(BEARER.length());
    }

    /**
     * 解析token，获得用户信息
     *
     * @param token token
     * @return 用户信息
     */
    @SuppressWarnings("unchecked")
    public UserDetails getUserFromToken(String token) {
        // 从token中获取user
        Claims claims = this.getClaimsFromToken(token);
        List<String> roles = (List<String>) claims.get(ROLES);
        String userId = (String) claims.get(USER_ID);
        String username = (String) claims.get(USERNAME);
        return new UserDetails(userId, null, username, roles);
    }

    /**
     * 判断token是否非法
     *
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}