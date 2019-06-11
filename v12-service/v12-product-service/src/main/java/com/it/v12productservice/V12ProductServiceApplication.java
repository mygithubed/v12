package com.it.v12productservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.it.v12.mapper")
@EnableDubbo
public class V12ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(V12ProductServiceApplication.class, args);
	}

}
