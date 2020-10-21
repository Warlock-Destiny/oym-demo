package com.oym.base.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangyd
 * @date 2019/10/9 17:24
 * @desc 返回值公共类
 */
@Data
public class Result<T> implements Serializable {
    public static final int OK = 0;
    public static final int FAIL = 1;
    public static final int ERROR = 2;

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> ok() {
        return build(OK, null, null);
    }

    public static <T> Result<T> ok(String msg) {
        return build(OK, msg, null);
    }

    public static <T> Result<T> okData(T t) {
        return Result.build(OK, null, t);
    }

    public static <T> Result<T> fail(String msg) {
        return build(FAIL, msg, null);
    }

    public static <T> Result<T> error(String msg) {
        return build(FAIL, msg, null);
    }

    public static <T> Result<T> build(int code, String msg, T data) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public Result<T> data(T t) {
        this.data = t;
        return this;
    }

    @JsonIgnore
    public boolean isOK() {
        return this.code == OK;
    }

    @JsonIgnore
    public boolean isFail() {
        return this.code == FAIL;
    }
}
