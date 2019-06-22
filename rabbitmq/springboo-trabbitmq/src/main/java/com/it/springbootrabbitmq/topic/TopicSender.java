package com.it.springbootrabbitmq.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author:曾志鹏
 * Date:2019/6/22
 * Time:10:42
 */
@Component
public class TopicSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.convertAndSend("my_TopicExchange","new.txt","深圳又下雨了！");
        rabbitTemplate.convertAndSend("my_TopicExchange","book.text","java宝典！");
    }
}
