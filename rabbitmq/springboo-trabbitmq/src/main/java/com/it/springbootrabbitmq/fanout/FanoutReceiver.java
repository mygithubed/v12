package com.it.springbootrabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Author:曾志鹏
 * Date:2019/6/22
 * Time:10:05
 */
@Component
public class FanoutReceiver {

    @RabbitHandler
    @RabbitListener(queues = "fanout_queue_one")
    public void processOne(String message){
        System.out.println("fanout_queue_one"+message);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout_queue_tow")
    public void processTow(String message){
        System.out.println("fanout_queue_tow"+message);
    }
}
