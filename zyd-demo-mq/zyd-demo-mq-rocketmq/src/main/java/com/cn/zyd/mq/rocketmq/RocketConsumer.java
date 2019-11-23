package com.cn.zyd.mq.rocketmq;

import com.cn.zyd.mq.BaseConsumer;
import com.cn.zyd.mq.MqClientAdapter;
import com.cn.zyd.mq.config.MqProperties;
import com.cn.zyd.mq.rocketmq.config.RocketMqProperties;
import com.cn.zyd.mq.rocketmq.config.RocketMqSuit;
import com.cn.zyd.mq.rocketmq.model.RocketMqCallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * @author zyd
 * @date 2019/11/20 11:27
 * @desc 可以使用spring注解@RabbitListener实现或者通过消费此操作实现
 */
@Slf4j
@Service
public class RocketConsumer extends MqClientAdapter<RocketMqProperties> implements BaseConsumer<RocketMqSuit, RocketMqCallback> {
    private List<DefaultMQPushConsumer> defaultMQPushConsumerList;

    public RocketConsumer() {
    }

    public RocketConsumer(RocketMqProperties mqProperties) {
        super(mqProperties);
    }

    @Override
    public void consumer(RocketMqSuit suit, Consumer<RocketMqCallback> consumer) {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer();
        pushConsumer.setNamesrvAddr(mqProperties.getHost() + ":" + mqProperties.getPort());
        pushConsumer.setConsumerGroup(mqProperties.getConsumerGroup());
        try {
            pushConsumer.subscribe(suit.getTopic(), suit.getTags());
            pushConsumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                msgs.forEach(msg -> {
                    RocketMqCallback rocketMqCallback = new RocketMqCallback();
                    rocketMqCallback.setBytes(msg.getBody());
                    try {
                        consumer.accept(rocketMqCallback);
                    } catch (Exception e) {
                        log.error("消费消息失败", e);
                    }
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            defaultMQPushConsumerList.add(pushConsumer);
            pushConsumer.start();
        } catch (MQClientException e) {
            log.error("客户端连接发生异常", e);
            pushConsumer.shutdown();
        }
    }

    @Override
    public void init() {
        defaultMQPushConsumerList = new CopyOnWriteArrayList<>();
    }

    @Override
    public void destroy() {
        defaultMQPushConsumerList.forEach(x -> {
            try {
                x.shutdown();
            } catch (Exception e) {
                log.error("消费者关闭失败", e);
            }
        });
    }
}
