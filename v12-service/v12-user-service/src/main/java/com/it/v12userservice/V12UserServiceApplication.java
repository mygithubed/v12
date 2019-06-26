package com.it.v12userservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.it.v12.mapper")
@EnableDubbo
public class V12UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(V12UserServiceApplication.class, args);
	}

}
