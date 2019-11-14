package com.cn.zyd.activiti.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.identity.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

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
//        org.springframework.security.core.Authentication authentication = Optional.of(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication).orElse(null);
//        if (!(authentication instanceof OAuth2Authentication)) {
//            log.warn("请求授权类型不是OAuth2Authentication类型");
//            return false;
//        }
//        Object userId = Optional.of((OAuth2Authentication) authentication).map(OAuth2Authentication::getUserAuthentication)
//                .map(x -> (Map) x.getDetails())
//                .map(x -> (Map) x.get("principal"))
//                .map(x -> x.get("userId")).orElse(null);
//        if (userId == null) {
//            log.warn("本次请求未带userId");
//            return false;
//        }
//        log.info("使用流程的用户userId:{}", userId);
//        Authentication.setAuthenticatedUserId(String.valueOf(userId));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Authentication.setAuthenticatedUserId(null);
    }

}
