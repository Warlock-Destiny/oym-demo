package com.cn.zyd.mq;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zyd
 * @date 2019/11/20 11:43
 * @desc rocketMq简单的启动类
 */
@Slf4j
public class RabbitMqClient implements BaseMqClient {
    @Override
    public void init() {
        log.info("init由spring完成");
    }

    @Override
    public void destroy() {
        log.info("destroy由spring完成");
    }
}
