package com.it.v12orderweb.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :曾志鹏
 * Date:2019/7/2
 * Time:17:53
 */
@Configuration
public class AliPayCliented {

    @Value("${alipay.privateKey}")
    private String privateKey;

    @Value("${alipay.publicKey}")
    private  String publicKey;

    @Bean
    public AlipayClient getAlipayClient(){

        AlipayClient alipayClient = new DefaultAlipayClient(
                "https://openapi.alipaydev.com/gateway.do",
                "2016101100658632",
                privateKey,
                "json",
                "utf-8",
                publicKey,
                "RSA2");

        return alipayClient;
    }

}
