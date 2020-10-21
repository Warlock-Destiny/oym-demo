package com.oym.component.web.exception;

/**
 * @author zyd
 * @date 2019/10/22 16:51
 * @desc 查询参数错误异常
 */
public class ParamException extends BaseException {

    public ParamException() {
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(String code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    /**
     * 不需要填充堆栈错误信息,减少堆栈追溯
     * 只需要base类中的code及msg元素就可以描述清楚异常问题 则不需要堆栈拼接
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
