package com.it.v12commonservice.services;

import com.alibaba.dubbo.config.annotation.Service;
import com.it.v12.api.IEmailService;
import com.it.v12.common.pojo.RsetBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 *@author:曾志鹏
 * Date:2019/6/25
 * Time:20:17
 */
@Service
public class IEmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**发件人的地址**/
    @Value("${mail.fromAddress}")
    private String fromAddress;

    /**
     * 邮件的发送
     * @param toAddress 收件人的地址
     * @param subject 邮件的标题
     * @param text 邮件的正文
     * @return 发送邮件的状态
     */
    @Override
    public RsetBean send(String toAddress, String subject, String text) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(text,true);
            javaMailSender.send(mimeMessage);

            return  new RsetBean("200","发送成功！");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return new RsetBean("404","发送失败了！");
    }
}
