package com.it.v12productservice.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Author:曾志鹏
 * Date:2019/6/11
 * Time:23:55
 * 分页功能的实现
 */
@Configuration
public class CommonConfig {

    @Bean
    public PageHelper getPageHelper(){

        PageHelper pageHelper = new PageHelper();
        //
        Properties properties = new Properties();
        properties.setProperty("dialect","mysql");
        properties.setProperty("reasonable","true");
        //
        pageHelper.setProperties(properties);

        return pageHelper;
    }
}
