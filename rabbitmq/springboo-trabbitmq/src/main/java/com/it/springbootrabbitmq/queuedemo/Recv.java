package com.it.springbootrabbitmq.queuedemo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Author:曾志鹏
 * Date:2019/6/21
 * Time:19:28
 */
@Component
@RabbitListener(queues = "queue_simple")
public class Recv {

    @RabbitHandler
    public void process(String message){
        System.out.println("收到的消息为："+message);
    }
}
