package com.it.springbootrabbitmq.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:曾志鹏
 * Date:2019/6/22
 * Time:10:33
 */
@Configuration
public class TopicConfig {

    @Bean
    public TopicExchange getTopicExchanges(){
        return new TopicExchange("my_TopicExchange");
    }

    @Bean
    public Queue getQueueTopic(){
        return new Queue("topic_Queue");
    }

    @Bean
    public Binding bindingExchange(Queue getQueueTopic,TopicExchange getTopicExchanges){
        return BindingBuilder.bind(getQueueTopic).to(getTopicExchanges).with("new.text");
    }
}
