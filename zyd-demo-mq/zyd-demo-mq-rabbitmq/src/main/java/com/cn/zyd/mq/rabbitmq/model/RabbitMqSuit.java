package com.cn.zyd.mq.rabbitmq.model;

import com.cn.zyd.mq.model.MqSuit;
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
