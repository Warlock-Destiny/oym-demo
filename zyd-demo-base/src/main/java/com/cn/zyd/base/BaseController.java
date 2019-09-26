package com.cn.zyd.base;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangyd
 * @date 2019/9/26 23:45
 * @desc
 */
public abstract class BaseController {

    public HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    private ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }
}
