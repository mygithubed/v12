package com.it.v12itemweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V12ItemWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(V12ItemWebApplication.class, args);
	}

}
