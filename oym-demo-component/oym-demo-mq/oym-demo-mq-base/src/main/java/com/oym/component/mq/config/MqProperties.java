package com.oym.component.mq.config;

import lombok.Data;

/**
 * @author zhangyd
 * @date 2019/11/20 11:10
 * @desc
 */
@Data
public class MqProperties {

    private String host;

    private int port;

    private String username;

    private String password;

}
