package com.cn.component.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author zyd
 * @date 2019/10/8 11:37
 * @desc
 */
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送文本邮件
     */
    public void sendSimpleMail(String from,String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

}

