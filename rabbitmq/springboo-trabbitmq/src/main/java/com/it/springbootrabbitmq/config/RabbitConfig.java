package com.it.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 声明队列
 * @author:曾志鹏
 * Date:2019/6/21
 * Time:19:19
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue getQueue(){
        return  new Queue("queue_simple");
    }



}
