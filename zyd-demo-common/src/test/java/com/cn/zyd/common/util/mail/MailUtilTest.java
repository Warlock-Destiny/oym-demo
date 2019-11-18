package com.cn.zyd.common.util.mail;

import com.cn.zyd.common.mail.MailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangyd
 * @date 2019/10/20 16:06
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailUtilTest {
    @Autowired
    private MailUtil mailUtil;

    @Test
    public void testSendMail(){
        mailUtil.sendSimpleMail("###@qq.com","测试邮件","测试邮件内容");
    }

}
