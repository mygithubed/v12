package com.it.rabbitmq.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Author:曾志鹏
 * Date:2019/6/20
 * Time:19:57
 */
public class Producer {

    /**
     *  声明交换机
     */
    private  static  String exchange_name = "exchange_name";

    public static void main(String[] agrs) throws IOException, TimeoutException {

        //创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.52.130");
        connectionFactory.setVirtualHost("/zzp");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("zzp");
        connectionFactory.setPassword("zzp");
        Connection connection = connectionFactory.newConnection();

        //2、创建本次交互的通道
        Channel channel = connection.createChannel();
        //3. 声明交换机
        channel.exchangeDeclare(exchange_name,"fanout");
        //4.将消息发送到交换机
        for (int i = 1; i <= 10; i++) {
            String message = "人如果没有梦想，跟咸鱼有什么区别"+i;
            channel.basicPublish(exchange_name,"",null,message.getBytes());
        }
        System.out.println("发送消息成功！");

    }
}
