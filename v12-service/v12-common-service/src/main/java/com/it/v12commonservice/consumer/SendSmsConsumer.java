package com.it.v12commonservice.consumer;

import com.it.v12.api.ISmsServie;
import com.it.v12.common.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : 曾志鹏
 * Date:2019/6/30
 * Time:16:42
 */
@Component
public class SendSmsConsumer {


    @Autowired
    private ISmsServie smsServie;

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConstant.EMAIL_QUEUE)
    public void smsProcess(Map<String,String> map){
        //执行发送短信的业务
         System.out.println(map.get("toPhone").toString());
        smsServie.sendSms(map.get("toPhone"),map.get("code"));
    }
}
