package com.oym.component.mq.rocketmq.config;

import com.oym.component.mq.model.MqSuit;
import lombok.Data;

/**
 * @author zyd
 * @date 2019/11/22 15:48
 * @desc
 */
@Data
public class RocketMqSuit extends MqSuit {

    private String topic;
    private String tags;
}
