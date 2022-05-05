package com.meng.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: Token工具类，用于生成token。
 * 用户登录拿到token然后访问我们的系统资源。
 * @author 孟举
 * @date 2022/4/2 17:23
 * @version 1.0
 */
@Component
public class TokenUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;
    /*
     *传入用户登录信息，生成token。
     **/
    public String generateToken(UserDetails details){
        Map<String,Object> map = new HashMap<>(2);
        map.put("username",details.getUsername());
        map.put("created", new Date());
        return this.generateJwt(map);
    }
    /*
     *根据荷载信息生成
     **/

    private String generateJwt(Map<String,Object> map){
        return Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512,secret)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }
    /*
     *根据token获取荷载信息。
     **/
    public Claims getTokenBody(String token){
        try{
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch(Exception e){
            return null;
        }

    }
    /*
     *根据token得到用户名.
     **/
    public String getUsernameByToken(String token){
        return (String) this.getTokenBody(token).get("username");
    }
    /*
     *根据token判断当前时间内，该token是否过期
     **/
    public boolean isExpiration(String token){
        return this.getTokenBody(token).getExpiration().before(new Date());
    }
    /*
     *刷新token
     **/
    public String refreshToken(String token){
        Claims claims = this.getTokenBody(token);
        claims.setExpiration(new Date());
        return this.generateJwt(claims);
    }
}
