package com.oym.component.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestMailApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(TestMailApplication.class, args);
        MailService mailService = configurableApplicationContext.getBean(MailService.class);
        mailService.sendSimpleMail("ywqian@esaipass.com", "ydzhang@easipass.com", "测试邮件", "测试邮件内容");
        Thread.currentThread().join();
    }

}
