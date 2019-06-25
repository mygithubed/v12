package com.it.rabbitmq.work;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Author:曾志鹏
 * Date:2019/6/20
 * Time:19:58
 */
public class MyConsumer {

    private  static  String QUEUE_NAME = "生产者！";

    public static void main(String[] agrs) throws IOException, TimeoutException {

        //创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.52.130");
        connectionFactory.setVirtualHost("/zzp");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("zzp");
        connectionFactory.setPassword("zzp");
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        Consumer consumer =  new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("消费者1："+message);
                //处理完后手工的回复结果  false:是否批量的确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //等回调
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
