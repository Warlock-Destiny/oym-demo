package com.cn.zyd.mq.model;

import lombok.Data;

/**
 * @author zyd
 * @date 2019/11/20 11:37
 * @desc
 */
@Data
public abstract class MqMessage {
    private String signId;
}
