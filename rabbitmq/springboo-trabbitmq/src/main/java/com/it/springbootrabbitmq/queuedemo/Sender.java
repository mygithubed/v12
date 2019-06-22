package com.it.springbootrabbitmq.queuedemo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者的代码
 * @author:曾志鹏
 * Date:2019/6/21
 * Time:19:24
 */
@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        //发送给交换机
        rabbitTemplate.convertAndSend("queue_simple","SpirngBoot整合RabbitMQ成功！");
    }
}
