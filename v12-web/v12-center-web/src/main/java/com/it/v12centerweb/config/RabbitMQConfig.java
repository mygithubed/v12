package com.it.v12centerweb.config;

import com.it.v12.common.constant.RabbitMQConstant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author:曾志鹏
 * Date:2019/6/21
 * Time:20:06
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 创建一个交换机
     * @return
     */
    public TopicExchange initTopic(){
        return new TopicExchange(RabbitMQConstant.CENTER_PRODUCT_ADD_EXCHANGE);
    }
}
