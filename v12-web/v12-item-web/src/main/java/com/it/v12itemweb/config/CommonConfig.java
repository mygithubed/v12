package com.it.v12itemweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建线程的方法
 * @author:曾志鹏
 * Date:2019/6/20
 * Time:16:56
 */
@Configuration
public class CommonConfig {

    /**
     * 初始化线程
     * @return
     */
    @Bean
    public ThreadPoolExecutor initThreadPoolExecutor(){
       //结合真实服务器硬件条件来设置
         int corePoolSize = Runtime.getRuntime().availableProcessors();
        //结论就是自己来创建线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                corePoolSize,corePoolSize*2,
                10, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100));

        return pool;
    }
}
