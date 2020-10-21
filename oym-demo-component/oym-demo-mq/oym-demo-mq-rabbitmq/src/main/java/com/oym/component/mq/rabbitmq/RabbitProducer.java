package com.oym.component.mq.rabbitmq;

import com.oym.component.mq.BaseProducer;
import com.oym.component.mq.MqClientAdapter;
import com.oym.component.mq.rabbitmq.model.RabbitMqMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zyd
 * @date 2019/11/20 11:27
 * @desc
 */
@Service
public class RabbitProducer extends MqClientAdapter implements BaseProducer<RabbitMqMessage> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(RabbitMqMessage rabbitMqMessage) {
        //暂时不知道signId要拿过来作什么
        String signId = rabbitMqMessage.getSignId();
        rabbitTemplate.convertAndSend(rabbitMqMessage.getExchange(), rabbitMqMessage.getRoutingKey(), rabbitMqMessage.getO());
    }

}
