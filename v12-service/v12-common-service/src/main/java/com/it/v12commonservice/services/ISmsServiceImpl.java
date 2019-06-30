package com.it.v12commonservice.services;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.it.v12.api.ISmsServie;
import com.it.v12.common.pojo.RsetBean;
import com.it.v12commonservice.sms.SendSms;

/**
 * 关于短信验证的service
 * @author : 曾志鹏
 * Date:2019/6/25
 * Time:20:18
 */
@Service
public class ISmsServiceImpl implements ISmsServie {

    @Override
    public RsetBean sendSms(String phoneNumber, String code) {
        SendSms sendSms = new SendSms();
        try {

            SendSmsResponse sendSmsResponse = sendSms.sendSms(phoneNumber,code);

            String code1 = sendSmsResponse.getCode();

            System.out.println("发送信息返回的状态码："+code1);

            String ok ="OK";

            if(ok.equals(code1)){
                return new RsetBean("200","短信发送成功！");
            }else{
                return new RsetBean("404","发送失败！");
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return  new RsetBean("404","短信发送失败！");
        }
    }


}
