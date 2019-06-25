package com.it.v12.api;

import com.it.v12.common.pojo.RsetBean;

/**
 * @author :曾志鹏
 * Date:2019/6/25
 * Time:20:01
 */
public interface IEmailService {
    /**
     * 邮件的发送
     * @param toAddress
     * @param subject
     * @param text
     * @return
     */
    RsetBean send(String toAddress,String subject,String text);
}
