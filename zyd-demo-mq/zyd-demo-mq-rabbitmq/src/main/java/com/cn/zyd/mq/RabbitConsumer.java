package com.cn.zyd.mq;

import com.cn.zyd.mq.model.RabbitMqCallback;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * @author zyd
 * @date 2019/11/20 11:27
 * @desc 可以使用spring注解@RabbitListener实现或者通过消费此操作实现
 */
@Slf4j
@Service
public class RabbitConsumer extends RabbitMqClient implements BaseConsumer<RabbitMqCallback> {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Override
    public void consumer(String queue, Consumer<RabbitMqCallback> consumer) {
        try {
            Channel channel = connectionFactory
                    .createConnection()
                    .createChannel(false);
            channel.basicConsume(
                    queue,
                    false,
                    new SimpleConsumer(channel, consumer)
            );
        } catch (IOException e) {
            log.error("发生流异常", e);
        }
    }

    private static class SimpleConsumer extends DefaultConsumer {

        private Consumer<RabbitMqCallback> consumer;

        private SimpleConsumer(Channel channel, Consumer<RabbitMqCallback> consumer) {
            super(channel);
            this.consumer = consumer;
        }

        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            Channel channel = getChannel();
            RabbitMqCallback rabbitMqCallback = new RabbitMqCallback();
            rabbitMqCallback.setBytes(body);
            consumer.accept(rabbitMqCallback);
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
