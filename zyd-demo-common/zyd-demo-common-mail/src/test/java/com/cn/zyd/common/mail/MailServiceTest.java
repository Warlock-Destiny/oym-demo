package com.cn.zyd.common.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail() {
        mailService.sendSimpleMail("zhangyd@ffcs.cn","测试邮件","测试邮件内容");
    }

}
