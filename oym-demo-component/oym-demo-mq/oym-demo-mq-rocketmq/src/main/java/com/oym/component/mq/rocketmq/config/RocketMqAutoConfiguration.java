package com.oym.component.mq.rocketmq.config;

import com.oym.component.mq.rocketmq.RocketConsumer;
import com.oym.component.mq.rocketmq.RocketProducer;
import org.apache.rocketmq.client.MQAdmin;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyd
 * @date 2019/11/22 10:09
 * @desc
 */
@Configuration
@EnableConfigurationProperties(RocketMqProperties.class)
@ConditionalOnClass(MQAdmin.class)
public class RocketMqAutoConfiguration {

    private RocketMqProperties rocketMqProperties;

    public RocketMqAutoConfiguration(RocketMqProperties rocketMqProperties) {
        this.rocketMqProperties = rocketMqProperties;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public RocketProducer rocketProducer(RocketMqProperties rocketMqProperties) {
        return new RocketProducer(rocketMqProperties);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public RocketConsumer rocketConsumer() {
        return new RocketConsumer(rocketMqProperties);
    }
}
