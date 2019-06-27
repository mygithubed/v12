package com.it.v12userservice;

import com.it.v12.common.pojo.RsetBean;
import com.it.v12.entity.TUser;
import com.it.v12userservice.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.image.RescaleOp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V12UserServiceApplicationTests {


	@Autowired
	private UserServiceImpl userService;

	@Test
	public void contextLoads() {
		TUser user = new TUser();
		user.setUsername("我们的世界");
		user.setPassword("123456");
		RsetBean checklogin = userService.checklogin(user);

		System.out.println(checklogin.getStatCodes());
		System.out.println(checklogin.getData().toString());
	}


	@Test
	public void checkLoginStarts(){
		RsetBean rsetBean = userService.checkLoginStarts("a5b91c7f-3319-4b5b-8f60-177ba34c5f87");
		System.out.println(rsetBean.getStatCodes());
		TUser user = (TUser) rsetBean.getData();
		if(user!=null){
			System.out.println(user.getUsername());
			System.out.println(user.getNiceName());
			System.out.println(user.getId());
		}else{
			System.out.println("不存在！");
		}
	}

	@Test
	public void delteTest(){
		RsetBean rsetBean = userService.loginOut("b6a649d6-890c-42af-b091-0009f7724255");
		System.out.println(rsetBean.getStatCodes());
		System.out.println(rsetBean.getData());
	}
}
