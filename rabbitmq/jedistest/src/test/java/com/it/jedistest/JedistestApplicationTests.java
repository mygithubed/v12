package com.it.jedistest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JedistestApplicationTests {

	@Test
	public void contextLoads() {

		Jedis jedis = new Jedis("192.168.52.130",6379);
		jedis.auth("zzp");
		jedis.set("hello","hello jedis!!");
		System.out.println(jedis.get("hello"));
	}

	@Test
	public void testother(){
		Jedis jedis = new Jedis("192.168.52.130",6379);

		jedis.auth("zzp");
		jedis.mset("key1","name");
		List<String> key1 = jedis.mget("key1");
		for (String s : key1) {
			System.out.println(s);
		}

	}

}
