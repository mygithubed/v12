package com.it.v12userservice;

import com.it.v12.entity.TUser;
import com.it.v12userservice.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V12UserServiceApplicationTests {


	@Autowired
	private UserServiceImpl userService;

	@Test
	public void contextLoads() {

	}

}
