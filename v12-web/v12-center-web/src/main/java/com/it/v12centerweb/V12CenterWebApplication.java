package com.it.v12centerweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


/**
 * @author zzp
 */
@SpringBootApplication
@Import(FdfsClientConfig.class)
@EnableDubbo
public class V12CenterWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(V12CenterWebApplication.class, args);
	}

}
