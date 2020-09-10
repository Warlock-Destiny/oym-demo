package com.cn.component.mq.rabbitmq.model;

import com.cn.component.mq.model.MqSuit;
import lombok.Data;

/**
 * @author zyd
 * @date 2019/11/22 15:48
 * @desc
 */
@Data
public class RabbitMqSuit extends MqSuit {

    private String queue;
}
