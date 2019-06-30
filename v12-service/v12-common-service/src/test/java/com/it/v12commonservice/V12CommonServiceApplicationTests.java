package com.it.v12commonservice;

import com.it.v12.api.IEmailService;
import com.it.v12.api.ISmsServie;
import com.it.v12.common.pojo.RsetBean;
import org.apache.zookeeper.KeeperException;
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

    @Autowired
	private ISmsServie iSmsServie;

	@Test
	public void contextLoads() {

	iEmailService.send("2493360725@qq.com","hello","整合的邮件发送OK了！");

	}

	@Test
	public  void sendSms(){
		String phonenum ="18370480788";
		String code ="2f9f";
		RsetBean rsetBean = iSmsServie.sendSms(phonenum, code);
		System.out.println(rsetBean.getStatCodes());
		System.out.println(rsetBean.getData());
	}

}
