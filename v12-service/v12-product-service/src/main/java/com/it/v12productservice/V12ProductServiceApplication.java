package com.it.v12productservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.it.v12.mapper")
public class V12ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(V12ProductServiceApplication.class, args);
	}

}
