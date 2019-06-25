package com.it.rabbitmq.work;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Author:曾志鹏
 * Date:2019/6/20
 * Time:21:07
 */
public class MyConsumer1 {

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
        //表示每次只接受的消息数量  起到限流的作用
        channel.basicQos(1);
        Consumer consumer =  new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String message = new String(body,"UTF-8");
                System.out.println("消费者2："+message);
                //处理完后手工的回复结果  false:是否批量的确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        // 将自动回复模式改为false
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
