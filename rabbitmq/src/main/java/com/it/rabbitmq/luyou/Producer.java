package com.it.rabbitmq.luyou;

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
    private  static  String exchange_name = "direct_exChange";

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
        //3. 声明交换机    路由的模式
        channel.exchangeDeclare(exchange_name,"direct");
        //4.将消息发送到交换机
        channel.basicPublish(exchange_name,"xinwen",null,"四川有地震了".getBytes());

        channel.basicPublish(exchange_name,"tianqi",null,"深圳下雨好几天了！".getBytes());
        System.out.println("发送消息成功！");

    }
}
