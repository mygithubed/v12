package com.it.springdataredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author:曾志鹏
 * Date:2019/6/24
 * Time:17:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class springdataredistest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void stringTest(){
        redisTemplate.opsForValue().set("target","nba");
        Object target = redisTemplate.opsForValue().get("target");
        System.out.println(target);
    }
}
