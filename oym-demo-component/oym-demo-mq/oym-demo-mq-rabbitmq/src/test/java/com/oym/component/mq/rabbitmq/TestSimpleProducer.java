package com.oym.component.mq.rabbitmq;

import com.oym.component.mq.BaseProducer;
import com.oym.component.mq.TestRabbitMqApplication;
import com.oym.component.mq.rabbitmq.model.RabbitMqMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangyd
 * @date 2019/11/21 14:29
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestRabbitMqApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestSimpleProducer {

    @Autowired
    private BaseProducer<RabbitMqMessage> baseProducer;

    @Test
    public void testSend() {
        RabbitMqMessage rabbitMqMessage = new RabbitMqMessage();
        rabbitMqMessage.setExchange("zyd.fanout");
        rabbitMqMessage.setRoutingKey("");
        rabbitMqMessage.setO("救命啊,我变成一条狗了");
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("EP_GATEWAY_FILE_NAME","678912345_MSDS_聚醚硅氧烷_聚醚硅氧烷.pdf");
        rabbitMqMessage.setProperties(messageProperties);
        baseProducer.send(rabbitMqMessage);
    }
}
