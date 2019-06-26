package com.it.v12commonservice.consumer;

import com.it.v12.api.IEmailService;
import com.it.v12.common.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *@author:曾志鹏
 * Date:2019/6/26
 * Time:11:50
 */
@Component
public class EmailConsumer {

    @Autowired
    private IEmailService emailService;

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConstant.EMAIL_QUEUE)
    public void emailProcess(Map<String,String> map){
        //发送邮件
        emailService.send(map.get("toAddress"),map.get("subject"),map.get("text"));
    }
}
