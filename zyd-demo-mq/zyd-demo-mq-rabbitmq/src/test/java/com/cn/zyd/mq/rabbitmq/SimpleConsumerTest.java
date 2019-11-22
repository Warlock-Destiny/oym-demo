package com.cn.zyd.mq.rabbitmq;

import com.cn.zyd.mq.BaseConsumer;
import com.cn.zyd.mq.Main;
import com.cn.zyd.mq.model.RabbitMqCallback;
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
public class SimpleConsumerTest {

    @Autowired
    private BaseConsumer<RabbitMqCallback> baseConsumer;

    @Test
    public void testConsumer() throws InterruptedException {
        baseConsumer.consumer("zyd", x -> {
            String msg = new String(x.getBytes(), StandardCharsets.UTF_8);
            System.out.println(msg);
        });
        Thread.sleep(10000L);
    }
}
