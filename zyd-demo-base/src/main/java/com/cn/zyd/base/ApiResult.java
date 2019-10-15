package com.cn.zyd.base;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zyd
 * @date 2019/10/7 21:01
 * @desc
 */
@Data
@Accessors(chain = true)
public class ApiResult<T> {

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;


    private int code;
    private String msg;
    private T data;


}
