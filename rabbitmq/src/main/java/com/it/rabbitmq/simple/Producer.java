package com.it.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Author:曾志鹏
 * Date:2019/6/20
 * Time:19:57
 */
public class Producer {

    public static void main(String[] agrs) throws IOException, TimeoutException {

        String QUEUE_NAME = "Cons_Name";
        //创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.52.130");
        connectionFactory.setVirtualHost("/zzp");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("zzp");
        connectionFactory.setPassword("zzp");
        Connection connection = connectionFactory.newConnection();
        //本次通讯的通道
        Channel channel = connection.createChannel();
        //3. 声明一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //4.发送消息
        String message = "人如果没有梦想，跟咸鱼有什么区别";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("发送消息成功！");

    }
}
