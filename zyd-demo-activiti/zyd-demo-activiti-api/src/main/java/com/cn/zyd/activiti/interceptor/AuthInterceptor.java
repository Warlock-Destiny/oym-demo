package com.cn.zyd.activiti.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.identity.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zyd
 * @date 2019/10/10 8:31
 * @desc activiti7 需要将userId存放到threadLocal以获取操作人
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Long userId = getUserId();
        Authentication.setAuthenticatedUserId(String.valueOf(userId));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Authentication.setAuthenticatedUserId(null);
    }

    //todo 获取userId
    private Long getUserId() {
        return 0L;
    }

}
