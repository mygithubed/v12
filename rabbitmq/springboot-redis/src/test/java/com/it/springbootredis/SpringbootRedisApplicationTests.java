package com.it.springbootredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;


	@Resource(name = "stringReadis")
	private  RedisTemplate<String,Object> stringReadis;

	@Test
	public void contextLoads() {
		redisTemplate.opsForValue().set("name","lisi");
		Object name = redisTemplate.opsForValue().get("name");
		System.out.println(name);
	}

	@Test
	public void testStringRedis(){
		stringReadis.opsForValue().set("user","wangwu");
		Object user = stringReadis.opsForValue().get("user");
		System.out.println(user);
	}

}
