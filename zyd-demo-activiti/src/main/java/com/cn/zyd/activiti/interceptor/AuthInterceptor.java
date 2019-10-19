package com.cn.zyd.activiti.interceptor;

import org.activiti.engine.impl.identity.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author zyd
 * @date 2019/10/10 8:31
 * @desc
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Object principal = Optional.of(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(org.springframework.security.core.Authentication::getPrincipal)
                .orElse(null);
        if (principal == null) {
            return true;
        }
        String username = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : principal.toString();
        Authentication.setAuthenticatedUserId(username);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Authentication.setAuthenticatedUserId(null);
    }
}
