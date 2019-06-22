package com.it.v12searchservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.it.v12.mapper")
public class V12SearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(V12SearchServiceApplication.class, args);
	}

}
