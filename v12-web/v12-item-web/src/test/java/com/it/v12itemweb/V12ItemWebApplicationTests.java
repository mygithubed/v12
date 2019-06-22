package com.it.v12itemweb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class V12ItemWebApplicationTests {

	@Autowired
	private Configuration configuration;

	@Test
	public void contextLoads() throws IOException, TemplateException {

		//获取模板对象
		Template template = configuration.getTemplate("hello.ftl");
		//设置数据
		Map<String ,Object> da = new HashMap<>();
		da.put("user","曾志鹏");
		//模板、数据显示到页面
		Writer w = new FileWriter("E:\\IDEA\\v12\\v12-web\\v12-item-web\\src\\main\\resources\\templates\\hello.ftl");
		template.process(da,w);
		System.out.println("生成静态页面OK");

	}

}
