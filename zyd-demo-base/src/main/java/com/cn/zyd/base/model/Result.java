package com.cn.zyd.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zyd
 * @date 2019/10/9 17:24
 * @desc 返回值公共类
 */
@Data
public class Result<T> implements Serializable {
    public static final int OK = 0;
    public static final int FAIL = 1;
    public static final int NO_AUTH = 403;

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> fail(String msg) {
        return Result.build(FAIL, msg);
    }

    public static <T> Result<T> ok(String msg) {
        return Result.build(OK, msg);
    }

    private static <T> Result<T> build(int code, String msg) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public Result<T> data(T t) {
        this.data = t;
        return this;
    }

    public boolean isOK() {
        return this.code == OK;
    }
    public boolean isFail() {
        return this.code == FAIL;
    }
}
