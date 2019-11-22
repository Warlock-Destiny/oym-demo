package com.cn.zyd.mq.model;

import com.cn.zyd.mq.model.MqCallback;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zyd
 * @date 2019/11/21 11:19
 * @desc
 */
@Data
@Accessors(chain = true)
public class RabbitMqCallback extends MqCallback {

    private byte[] bytes;
}
