package com.cn.zyd.base;

import java.io.Serializable;

/**
 * @author zyd
 * @date 2019/10/9 17:24
 * @desc
 */
public class Result<T> implements Serializable {
    public static final int OK = 0;
    public static final int FAIL = 500;
    public static final int NO_AUTH = 403;

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(String msg) {
        Result<T> result = new Result<T>();
        result.setCode(OK);
        result.setMsg(msg);
        return result;
    }

    public Result<T> data(T t) {
        this.data = t;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
