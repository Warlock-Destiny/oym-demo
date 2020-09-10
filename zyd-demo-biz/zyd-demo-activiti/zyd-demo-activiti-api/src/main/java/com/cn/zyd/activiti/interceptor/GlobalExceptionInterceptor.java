package com.cn.zyd.activiti.interceptor;

import com.cn.zyd.base.exception.ParamException;
import com.cn.zyd.base.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zyd
 * @date 2019/10/22 16:49
 * @desc 全局异常处理类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionInterceptor {

    @ResponseBody
    @ExceptionHandler(ParamException.class)
    public Result queryParamExceptionHandler(ParamException paramException) {
        String msg = paramException.getMessage();
        log.error("查询参数异常:{}", msg);
        return Result.fail(msg);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("系统全局异常:", e);
        return Result.fail("系统发生异常");
    }
}
