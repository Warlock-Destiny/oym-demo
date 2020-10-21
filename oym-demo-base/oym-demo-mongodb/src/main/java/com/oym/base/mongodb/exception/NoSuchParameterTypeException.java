package com.oym.base.mongodb.exception;

/**
 * @Description: 备份操作接口
 * @author: zhangyd
 * @date: 2020/09/17 14:51
 */
public class NoSuchParameterTypeException extends Exception {
    public NoSuchParameterTypeException() {
    }

    public NoSuchParameterTypeException(String message) {
        super(message);
    }

    public NoSuchParameterTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchParameterTypeException(Throwable cause) {
        super(cause);
    }

    public NoSuchParameterTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
