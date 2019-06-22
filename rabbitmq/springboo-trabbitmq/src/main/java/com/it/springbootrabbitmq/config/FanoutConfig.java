package com.it.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:曾志鹏
 * Date:2019/6/22
 * Time:10:59
 */
@Configuration
public class FanoutConfig {

    @Bean
    public Queue getQueueOne(){
        return  new Queue("fanout_queue_one");
    }

    @Bean
    public Queue getQueueTow(){
        return new Queue("fanout_queue_tow");
    }

    @Bean
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("FanoutExchange_First");
    }

    @Bean
    public Binding bindingExchangeOne(Queue getQueueOne, FanoutExchange getFanoutExchange){
        return BindingBuilder.bind(getQueueOne).to(getFanoutExchange);
    }

    @Bean
    public Binding bindingExchangeTow(Queue getQueueTow,FanoutExchange getFanoutExchange){
        return  BindingBuilder.bind(getQueueTow).to(getFanoutExchange);
    }


}
