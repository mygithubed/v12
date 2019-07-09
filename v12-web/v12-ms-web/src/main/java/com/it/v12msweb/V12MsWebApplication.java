package com.it.v12msweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.it.v12msweb.mapper")
@EnableCaching
@EnableScheduling
public class V12MsWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(V12MsWebApplication.class, args);
	}

}
