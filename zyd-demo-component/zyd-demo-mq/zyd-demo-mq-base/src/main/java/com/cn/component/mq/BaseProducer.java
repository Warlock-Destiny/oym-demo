package com.cn.component.mq;

import com.cn.component.mq.model.MqMessage;

/**
 * @author zyd
 * @date 2019/11/20 11:28
 * @desc 基础提供类 提供发送消息的方法 提供初始化方法
 */
public interface BaseProducer<T extends MqMessage> extends BaseMqClient {

    void send(T t);

}
