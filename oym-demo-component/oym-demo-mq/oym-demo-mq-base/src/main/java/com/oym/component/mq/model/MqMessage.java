package com.oym.component.mq.model;

import lombok.Data;

/**
 * @author zhangyd
 * @date 2019/11/20 11:37
 * @desc
 */
@Data
public abstract class MqMessage {
    private String signId;
}
