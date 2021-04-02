package com.oym.component.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author zhangyd
 * @date 2019/10/8 11:37
 * @desc
 */
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送文本邮件
     */
    public void sendSimpleMail(String from, String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

    /**
     * 发送html邮件
     */
    public void sendHtmlMail(String from, String to, String subject, String content) {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "UTF-8");
        try {
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content,true);
            javaMailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

