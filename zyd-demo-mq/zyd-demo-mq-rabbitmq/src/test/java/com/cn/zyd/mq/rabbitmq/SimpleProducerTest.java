package com.cn.zyd.mq.rabbitmq;

import com.cn.zyd.mq.BaseProducer;
import com.cn.zyd.mq.Main;
import com.cn.zyd.mq.model.RabbitMqMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zyd
 * @date 2019/11/21 14:29
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SimpleProducerTest {

    @Autowired
    private BaseProducer<RabbitMqMessage> baseProducer;

    @Test
    public void testSend() {
        RabbitMqMessage rabbitMqMessage = new RabbitMqMessage();
        rabbitMqMessage.setExchange("fanout");
        rabbitMqMessage.setRoutingKey("");
        rabbitMqMessage.setO("救命啊,我变成一条狗了");
        baseProducer.send(rabbitMqMessage);
    }
}
