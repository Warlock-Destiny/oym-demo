package com.cn.zyd.mq;

/**
 * @author zyd
 * @date 2019/11/20 11:33
 * @desc 基础的mq客户端
 */
public interface BaseMqClient {

    /**
     * 初始化客户端
     */
    void init();

    /**
     * 关闭
     */
    void destroy();
}
