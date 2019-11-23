package com.cn.zyd.mq.rocketmq;

import com.cn.zyd.mq.BaseProducer;
import com.cn.zyd.mq.MqClientAdapter;
import com.cn.zyd.mq.rocketmq.config.RocketMqProperties;
import com.cn.zyd.mq.rocketmq.model.RocketMqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author zyd
 * @date 2019/11/22 9:56
 * @desc
 */
@Slf4j
public class RocketProducer extends MqClientAdapter<RocketMqProperties> implements BaseProducer<RocketMqMessage> {

    private DefaultMQProducer defaultMQProducer;

    public RocketProducer() {
    }

    public RocketProducer(RocketMqProperties mqProperties) {
        super(mqProperties);
    }

    @Override
    public void send(RocketMqMessage rocketMqMessage) {
        if (rocketMqMessage.getBytes() == null) {
            return;
        }
        String topic = rocketMqMessage.getTopic();
        String tags = getDefault(rocketMqMessage.getTags());
        String keys = getDefault(rocketMqMessage.getKeys());
        try {
            SendResult sendResult = defaultMQProducer.send(new Message(topic, tags, keys, rocketMqMessage.getBytes()));
            if (sendResult.getSendStatus() != SendStatus.SEND_OK) {
                log.info("发送失败,signId:{}，msgId:{}", rocketMqMessage.getSignId(), sendResult.getMsgId());
            }
        } catch (MQClientException e) {
            log.error("客户端连接发生异常", e);
        } catch (RemotingException e) {
            log.error("远程mq服务端连接发生异常", e);
        } catch (MQBrokerException e) {
            log.error("远程mq broker服务端连接发生异常", e);
        } catch (InterruptedException e) {
            log.error("中断异常", e);
        }
    }

    @Override
    public void init() {
        if (!mqProperties.isHasProducer()) {
            log.info("配置没有配置生产者:spring.rocketmq.hasProducer");
            return;
        }
        defaultMQProducer = new DefaultMQProducer(mqProperties.getProducerGroup());
        defaultMQProducer.setNamesrvAddr(mqProperties.getHost() + ":" + mqProperties.getPort());
        try {
            defaultMQProducer.start();
            log.info("生产者初始化成功");
        } catch (MQClientException e) {
            log.info("发生异常", e);
            destroy();
        }
    }

    @Override
    public void destroy() {
        if (defaultMQProducer != null) {
            defaultMQProducer.shutdown();
        }
    }

    private String getDefault(String data) {
        return data == null ? "" : data;
    }

}
