package com.cn.zyd.mq.rocketmq.config;

import com.cn.zyd.mq.model.MqSuit;
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
