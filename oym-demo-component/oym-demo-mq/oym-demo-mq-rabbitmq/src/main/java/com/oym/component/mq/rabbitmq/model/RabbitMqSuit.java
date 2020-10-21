package com.oym.component.mq.rabbitmq.model;

import com.oym.component.mq.model.MqSuit;
import lombok.Data;

/**
 * @author zhangyd
 * @date 2019/11/22 15:48
 * @desc
 */
@Data
public class RabbitMqSuit extends MqSuit {

    private String queue;
}
