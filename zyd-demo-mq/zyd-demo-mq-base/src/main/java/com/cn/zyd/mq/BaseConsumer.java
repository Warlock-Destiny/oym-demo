package com.cn.zyd.mq;


import com.cn.zyd.mq.model.MqCallback;

import java.util.function.Consumer;

/**
 * @author zyd
 * @date 2019/11/21 11:04
 * @desc
 */
public interface BaseConsumer<C extends MqCallback> extends BaseMqClient {

    void consumer(String queue, Consumer<C> function);


}
