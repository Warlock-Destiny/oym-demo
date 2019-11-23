package com.cn.zyd.mq.rocketmq.config;

import com.cn.zyd.mq.config.MqProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zyd
 * @date 2019/11/22 11:23
 * @desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties("spring.rocketmq")
public class RocketMqProperties extends MqProperties {

    private boolean hasProducer;
    private boolean hasConsumer;

    private String producerGroup;
    private String consumerGroup;


}
