package com.it.springbootrabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Author:曾志鹏
 * Date:2019/6/22
 * Time:10:46
 */
@Component
public class TopicReciver {

    @RabbitHandler
    @RabbitListener(queues = "topic_Queue")
    public void getMessage(String message){
        System.out.println("收到的消息为"+message);
    }
}

