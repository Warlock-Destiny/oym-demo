package com.oym.component.web.model;

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
    private static final int OK = 0;
    private static final int ERROR = 500;

    private int code;
    private String msg;
    private T data;

    //-------------------------------------------------------------------------------------
    public static <T> Result<T> ok() {
        return build(OK, null, null);
    }

    public static <T> Result<T> ok(T t) {
        return build(OK, null, t);
    }

    public static <T> Result<T> ok(String msg, T data) {
        return build(OK, msg, data);
    }

    //-------------------------------------------------------------------------------------
    public static <T> Result<T> fail() {
        return build(ERROR, null, null);
    }

    public static <T> Result<T> fail(String msg) {
        return build(ERROR, msg, null);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return build(code, msg, null);
    }

    //-------------------------------------------------------------------------------------
    @JsonIgnore
    public boolean isOK() {
        return this.code == OK;
    }

    @JsonIgnore
    public boolean isFail() {
        return this.code != OK;
    }

    private static <T> Result<T> build(int code, String msg, T data) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
