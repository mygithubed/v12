package com.it.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Author:曾志鹏
 * Date:2019/6/20
 * Time:19:58
 * 手动的形式
 * */
public class MyConsumer {


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

        Channel channel = connection.createChannel();
        //创建一个消费者的对象
        Consumer consumer =  new DefaultConsumer(channel){
        //等着队列有消息后，自动回调
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println(message);
            }
        };
        //让消费者监听队列  参数1：队列的名称 参数2：自动应答，自动告知服务器消息已经被消费 参数3：那个消费端关注队列
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
