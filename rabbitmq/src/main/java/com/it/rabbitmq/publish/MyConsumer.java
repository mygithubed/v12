package com.it.rabbitmq.publish;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Author:曾志鹏
 * Date:2019/6/20
 * Time:19:58
 */
public class MyConsumer {

    /**
     *  声明交换机 要和 生产者的一致
     */
    private  static  String exchange_name = "exchange_name";
    /**
     * 声明队列
     */
    private  static  String queue_name = "queue_nameTow";

    public static void main(String[] agrs) throws IOException, TimeoutException {

        //创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.52.130");
        connectionFactory.setVirtualHost("/zzp");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("zzp");
        connectionFactory.setPassword("zzp");
        Connection connection = connectionFactory.newConnection();
        //创建消息通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(queue_name,false,false,false,null);
        //消息监听队列的作用
        Consumer consumer =  new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("消费者1："+message);

                //处理完后手工的回复结果  false:是否批量的确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //绑定队列和交换机
        channel.queueBind(queue_name,exchange_name,"");
        //等回调
        channel.basicConsume(queue_name,false,consumer);
    }
}
