package com.cn.zyd.mq.rocketmq.model;

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
public class RocketMqCallback extends MqCallback {

    private byte[] bytes;
}
