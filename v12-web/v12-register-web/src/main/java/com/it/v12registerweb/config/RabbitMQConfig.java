package com.it.v12registerweb.config;

import com.it.v12.common.constant.RabbitMQConstant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 曾志鹏
 * Date:2019/6/25
 * Time:21:28a
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 声明交换机
     * @return 交换机的名称
     */
    @Bean
    public TopicExchange getTopicExchanges(){
        return new TopicExchange(RabbitMQConstant.REGISTER_EXCHAGE);
    }
}
