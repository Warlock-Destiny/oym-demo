package com.cn.zyd.mq.exception;

import com.cn.zyd.base.exception.BaseException;

/**
 * @author zyd
 * @date 2019/11/20 11:29
 * @desc mq异常类
 */
public class MqException extends BaseException {

    public MqException() {
        super();
    }

    public MqException(Throwable cause) {
        super(cause);
    }

    public MqException(String message) {
        super(message);
    }

    public MqException(String message, Throwable cause) {
        super(message, cause);
    }

    public MqException(String code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

}
