package com.it.v12.common.constant;

/**
 * Author:曾志鹏
 * Date:2019/6/21
 * Time:20:15
 */
public interface RabbitMQConstant {

    /**
     * 添加商品的交换机
     */
    String CENTER_PRODUCT_ADD_EXCHANGE ="center_product_add_exchange";

    String PRODUCT_SEARCH_QUEUE ="product_search_queue";


    String REGISTER_EXCHAGE ="register_exchange";

    String SMS_REGISTER_EXCHAGE ="phone_register_exchange";

    String EMAIL_QUEUE ="email_queue";

    String SENDSMS_QUEUE ="sms_queue";

}
