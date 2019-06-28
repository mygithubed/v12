package com.it.v12userservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jjwt的工具类
 *
 * @author : 曾志鹏
 * Date:2019/6/28
 * Time:20:30
 */
public class JwtUtils {

    /**密钥由调用方来决定**/
    private String secretKey;

    /**有效期也由调用方来决定**/
    private long ttl;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    /**
     *  生成令牌
     * @param id 用户的ID
     * @param subject
     * @return 返回
     */
    public String createJwtToken(String id,String subject){
        long now = System.currentTimeMillis();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id).setSubject(subject)
                .setIssuedAt(new Date(now))
                .signWith(SignatureAlgorithm.HS256,secretKey);
        if(ttl > 0){
            jwtBuilder.setExpiration(new Date(now+ttl));
        }
        return jwtBuilder.compact();
    }

    /**
     * 解析令牌
     * @param jwtToken 传入的Tocken
     * @return
     */
    public Claims parseJwtToken(String jwtToken){
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(jwtToken).getBody();
    }
}
