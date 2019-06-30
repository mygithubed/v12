package com.it.v12.api;

import com.it.v12.common.pojo.RsetBean;

/**
 *关于短信的验证的接口
 * @author : 曾志鹏
 * Date:2019/6/25
 * Time:20:02
 */
public interface ISmsServie {
    /**
     * 发送短信验证码
     * @param phoneNumber 手机号
     * @param code 验证码
     * @return 返回状态
     */
    RsetBean sendSms(String phoneNumber,String code);
}
