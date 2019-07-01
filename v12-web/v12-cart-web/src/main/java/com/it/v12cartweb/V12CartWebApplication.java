package com.it.v12cartweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V12CartWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(V12CartWebApplication.class, args);
	}

}
