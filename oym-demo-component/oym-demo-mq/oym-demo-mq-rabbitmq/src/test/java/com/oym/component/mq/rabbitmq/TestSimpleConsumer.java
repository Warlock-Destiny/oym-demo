package com.oym.component.mq.rabbitmq;

import com.oym.component.mq.RabbitMqApplication;
import com.oym.component.mq.rabbitmq.model.RabbitMqSuit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangyd
 * @date 2019/11/21 14:29
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMqApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TestSimpleConsumer {

    @Autowired
    private RabbitConsumer baseConsumer;

    @Test
    public void testConsumer() throws InterruptedException {
        RabbitMqSuit rabbitMqSuit = new RabbitMqSuit();
        rabbitMqSuit.setQueue("queue");
        baseConsumer.consumer(rabbitMqSuit, x -> {
            String msg = new String(x.getBytes(), StandardCharsets.UTF_8);
            System.out.println(msg);
        });
        Thread.sleep(10000L);
    }
}
