package com.oym.common.mail;

import com.oym.component.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestMailApplication.class)
public class TestMailService {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail() {
        mailService.sendSimpleMail("ywqian@esaipass.com", "ydzhang@esaipass.com", "测试邮件", "测试邮件内容");
    }

    @Test
    public void testHtml() {
        mailService.sendHtmlMail("DSSPMS<ydzhang@esaipass.com>", "ydzhang@easipass.com", "数据服务共享平台流程提醒",
                "<html>\n" +
                        "<body>\n" +
                        "<div class=\"box-content\" style=\"width: 80%; margin: 20px auto; max-width: 800px; min-width: 600px;background-color: #F8F8FF\">\n" +
                        "    <div class=\"info-top\" style=\"padding: 15px 25px;\n" +
                        "                                 border-top-left-radius: 10px;\n" +
                        "                                 border-top-right-radius: 10px;\n" +
                        "\t\t\t\t\t\t\t\t border:1px solid #ddd;\n" +
                        "                                 background: #1E90FF;\n" +
                        "                                 color: #000;\n" +
                        "                                 overflow: hidden;\n" +
                        "                                 line-height: 32px;\">\n" +
                        "        <strong>数据服务申请通知</strong>\n" +
                        "\t</div>\n" +
                        "    <div class=\"info-wrap\" style=\"border-bottom-left-radius: 10px;\n" +
                        "                                  border-bottom-right-radius: 10px;\n" +
                        "                                  border:1px solid #ddd;\n" +
                        "                                  padding: 15px 15px 20px;\">\n" +
                        "        <div class=\"tips\">\n" +
                        "            <p style=\" list-style: 160%; margin: 10px 0;\">Hi,数据服务申请流程已经到您这里了。</p>\n" +
                        "        </div>\n" +
                        "\t\t<table border=\"1\" style=\"width:100%; border-collapse:collapse\">\n" +
                        "\t\t  <tr>\n" +
                        "\t\t\t<th width=\"24%\">编号</th>\n" +
                        "\t\t\t<th width=\"24%\">时间</th>\n" +
                        "\t\t\t<th width=\"24%\">操作人</th>\n" +
                        "\t\t\t<th width=\"24%\">状态</th>\n" +
                        "\t\t  </tr>\n" +
                        "\t\t  <tr>\n" +
                        "\t\t\t<th width=\"24%\"><a href=\"http://192.168.120.40/dssp//#/template/orderApprove?key=205\">SO827580264594014208</a></th>\n" +
                        "\t\t\t<th width=\"24%\">2021-04-02 16:28:30</th>\n" +
                        "\t\t\t<th width=\"24%\">EP_MOCK</th>\n" +
                        "\t\t\t<th width=\"24%\">初审</th>\n" +
                        "\t\t  </tr>\n" +
                        "\t\t</table>\n" +
                        "    </div>\n" +
                        "</div>\n" +
                        "</body>\n" +
                        "<html>");
    }

}
