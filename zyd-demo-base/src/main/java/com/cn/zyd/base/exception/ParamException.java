package com.cn.zyd.base.exception;

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
     */
    @Override
    public Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }
}
