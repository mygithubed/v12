package com.it.springbootrabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author:曾志鹏
 * Date:2019/6/22
 * Time:10:01
 */
@Component
public class FanoutSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  void send(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("FanoutExchange_First","","发送了消息到交换机中！！！！"+(i+1)+"次");
        }
    }
}
