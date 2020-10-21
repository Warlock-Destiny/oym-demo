package com.oym.component.mq;


import com.oym.component.mq.model.MqCallback;
import com.oym.component.mq.model.MqSuit;

import java.util.function.Consumer;

/**
 * @author zhangyd
 * @date 2019/11/21 11:04
 */
public interface BaseConsumer<S extends MqSuit, C extends MqCallback> extends BaseMqClient {

    void consumer(S s, Consumer<C> function);

}
