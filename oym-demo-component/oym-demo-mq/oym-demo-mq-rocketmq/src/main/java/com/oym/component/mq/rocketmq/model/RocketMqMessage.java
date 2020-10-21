package com.oym.component.mq.rocketmq.model;

import com.oym.component.mq.model.MqMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.function.Consumer;

/**
 * @author zyd
 * @date 2019/11/21 10:50
 * @desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RocketMqMessage extends MqMessage {
    private String topic;
    private String tags;
    private String keys;
    private byte[] bytes;
    private Consumer<Throwable> fallCallBack;

}
