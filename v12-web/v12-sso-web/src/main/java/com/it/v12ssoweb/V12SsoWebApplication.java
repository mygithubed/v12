package com.it.v12ssoweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class V12SsoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(V12SsoWebApplication.class, args);
	}

}
