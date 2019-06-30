package com.it.v12commonservice.config;

import com.it.v12.common.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :  曾志鹏
 * Date:2019/6/25
 * Time:20:27
 */
@Configuration
public class RabbitMQConfig {

    /**
     *   声明队列
     */
    @Bean
    public Queue initQueue(){
        return  new Queue(RabbitMQConstant.EMAIL_QUEUE,true,false,false);
    }



    /**
     * 声明交换机 如果交换机不存在才会创建
     * @return 交换机
     */
    @Bean
    public TopicExchange initTopic(){
        return new TopicExchange(RabbitMQConstant.REGISTER_EXCHAGE);
    }


    /**
     * 绑定交换机
     * @param initQuue 队列的名称
     * @param topicExchange 交换机
     * @return 返回绑定
     */
    @Bean
    public Binding initBinding(Queue initQuue,TopicExchange initTopic){
      return  BindingBuilder.bind(initQuue).to(initTopic).with("user_add");
    }

    /**
     * 绑定交换机
     * @param initQuue 队列的名称
     * @param topicExchange 交换机
     * @return 返回绑定
     */
    @Bean
    public Binding initBindings(Queue initQuue,TopicExchange initTopic){
        return  BindingBuilder.bind(initQuue).to(initTopic).with("user_add_phone");
    }
}
