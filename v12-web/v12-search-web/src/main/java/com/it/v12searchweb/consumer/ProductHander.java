package com.it.v12searchweb.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.it.v12.api.ISearchApi;
import com.it.v12.common.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 针对平台系统商品服务发送的消息做处理的消费端
 * @author:曾志鹏
 * Date:2019/6/21
 * Time:20:42
 */
@Component
@RabbitListener(queues = RabbitMQConstant.PRODUCT_SEARCH_QUEUE)
public class ProductHander {

    @Reference
    private ISearchApi searchApi;

    @RabbitHandler
    public void productAdd(Long id){
        //同步id
        searchApi.queryDataById(id);
        System.out.println(id);
    }
}
