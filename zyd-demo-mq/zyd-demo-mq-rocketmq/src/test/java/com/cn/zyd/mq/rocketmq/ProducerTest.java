package com.cn.zyd.mq.rocketmq;

import com.cn.zyd.mq.Main;
import com.cn.zyd.mq.rocketmq.model.RocketMqMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

/**
 * @author zyd
 * @date 2019/11/21 14:29
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ProducerTest {

    @Autowired
    private RocketProducer baseProducer;

    @Test
    public void testSend() {
        RocketMqMessage rocketMqMessage = new RocketMqMessage();
        rocketMqMessage.setTopic("topic1");
        rocketMqMessage.setBytes("救命啊，我变成一条狗了!".getBytes(StandardCharsets.UTF_8));
        baseProducer.send(rocketMqMessage);
    }
}
