package com.it.v12commonservice;

import com.it.v12.api.IEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V12CommonServiceApplicationTests {

	@Autowired
	private IEmailService iEmailService;

	@Test
	public void contextLoads() {

	iEmailService.send("2493360725@qq.com","hello","整合的邮件发送OK了！");

	}

}
