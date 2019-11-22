package com.cn.zyd.mq;

import com.cn.zyd.mq.model.RabbitMqMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zyd
 * @date 2019/11/20 11:27
 * @desc
 */
@Service
public class RabbitProducer extends RabbitMqClient implements BaseProducer<RabbitMqMessage> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(RabbitMqMessage rabbitMqMessage) {
        rabbitTemplate.convertAndSend(rabbitMqMessage.getExchange(), rabbitMqMessage.getRoutingKey(), rabbitMqMessage.getO());
    }

}
