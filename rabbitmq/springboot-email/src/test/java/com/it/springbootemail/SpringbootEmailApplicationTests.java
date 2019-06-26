package com.it.springbootemail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootEmailApplicationTests {


	@Autowired
	private JavaMailSender javaMailSender;


	@Autowired
	private TemplateEngine templateEngine;


	@Value("${mail.fromAddress}")
	private String fromAddress;



	/**
	 * 发送的是普通邮件
	 */
	@Test
	public void contextLoads() {
		SimpleMailMessage  simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromAddress);
		simpleMailMessage.setTo(fromAddress);
		simpleMailMessage.setSubject("写给自己的一封信！");
		simpleMailMessage.setText("希望随着时间的变化，增长的不仅仅是年龄！！");
		javaMailSender.send(simpleMailMessage);
	}


	@Test
	public void sendHtml(){

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			helper.setFrom(fromAddress);
			helper.setTo("38599557@qq.com");
			helper.setSubject("hello");
			helper.setText("<a href='https://www.baidu.com/sf/vsearch?pd=video&tn=vsearch&lid=8c84939d0001a143&ie=utf-8&rsv_pq=8c84939d0001a143&wd=%E6%8A%96%E9%9F%B3&rsv_spt=5&rsv_t=a4149hqv2F95Z7Sec3lPVACNobTBJkOH3ILScP5No%2F8NAE5M1roEV8pEs8t2CWhyhCk&rsv_bp=1&f=8'>点击我吧！</a>",true);
			javaMailSender.send(mimeMessage);
			System.out.println("发送邮件OK了!");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void sendHtml2(){

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		//模板的生成
		Context context = new Context();
		context.setVariable("username","花样骚年");
		String uuid = UUID.randomUUID().toString();
		String url =  new StringBuilder("http://localhost:9094/user/activer/").append(uuid).toString();
		context.setVariable("url",url);
		String text = templateEngine.process("email.html",context);

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			helper.setFrom(fromAddress);
			helper.setTo(fromAddress);
			helper.setSubject("疯狂购物系统激活邮件");
			helper.setText(text,true);
			javaMailSender.send(mimeMessage);
			System.out.println("发送邮件OK了!");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
