package com.it.v12cartservice;

import com.it.v12.common.pojo.RsetBean;
import com.it.v12.pojo.CartItem;
import com.it.v12cartservice.service.CartServiceImpl;
import com.sun.org.apache.xpath.internal.SourceTree;
import io.lettuce.core.output.ScoredValueScanOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.event.TreeSelectionEvent;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V12CartServiceApplicationTests {


	@Resource(name="myRedisTemlate")
	private RedisTemplate redisTemplate;

	@Autowired
	public CartServiceImpl cartService;


	@Test
	public void contextLoads() {
		String uuid= UUID.randomUUID().toString();
		RsetBean rsetBean = cartService.add("d13ba3e5-524a-4d33-a06c-8fbbab4c80d7", 1L, 1506);
		System.out.println(rsetBean.getStatCodes());
		System.out.println(rsetBean.getData());
		System.out.println(uuid);
	}


	@Test
	public void getTest(){
		Map<Object,Object> entries = redisTemplate.opsForHash().entries("user:cart:d13ba3e5-524a-4d33-a06c-8fbbab4c80d7");
		Set<Map.Entry<Object, Object>> entries1 = entries.entrySet();
		for (Map.Entry<Object, Object> entry : entries1) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}

	@Test
	public void queryTest(){
		RsetBean query = cartService.query("d13ba3e5-524a-4d33-a06c-8fbbab4c80d7");

		System.out.println(query.getStatCodes());
		TreeSet<CartItem> data =(TreeSet<CartItem>)query.getData();
		for (CartItem item : data) {
			System.out.println(item);
		}
	}

	@Test
	public void del(){
		RsetBean query = cartService.remove("d13ba3e5-524a-4d33-a06c-8fbbab4c80d7",19L);

		System.out.println(query.getStatCodes());
		System.out.println(query.getData());


	}

	@Test
	public void update(){
		RsetBean query = cartService.update("d13ba3e5-524a-4d33-a06c-8fbbab4c80d7",2L,22200);
		System.out.println(query.getStatCodes());
		System.out.println(query.getData());


	}
}
