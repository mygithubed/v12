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
     * @param toAddress 收件人的邮箱
     * @param subject 邮件的主题
     * @param text 邮件的正文
     * @return 返回发送的状态
     */
    RsetBean send(String toAddress,String subject,String text);
}
