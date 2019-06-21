package com.it.v12searchweb.config;

import com.it.v12.common.constant.RabbitMQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:曾志鹏
 * Date:2019/6/21
 * Time:20:27
 */
@Configuration
public class RabbitMQConfig {

    /**
     *   声明队列
     */
    @Bean
    public Queue initQueue(){
        return  new Queue(RabbitMQConstant.PRODUCT_SEARCH_QUEUE,true,false,false);
    }


    /**
     * 声明交换机 如果交换机不存在才会创建
     * @return
     */
    @Bean
    public TopicExchange initTopic(){
        return new TopicExchange(RabbitMQConstant.CENTER_PRODUCT_ADD_EXCHANGE);
    }


    /**
     * 绑定交换机
     * @param initQuue
     * @param topicExchange
     * @return
     */
    @Bean
    public Binding initBinding(Queue initQuue,TopicExchange topicExchange){
      return  BindingBuilder.bind(initQuue).to(topicExchange).with("product_add");
    }
}
