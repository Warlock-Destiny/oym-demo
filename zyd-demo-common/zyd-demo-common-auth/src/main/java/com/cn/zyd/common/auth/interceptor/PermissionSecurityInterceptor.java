package com.cn.zyd.common.auth.interceptor;

import com.cn.zyd.common.auth.service.AccessDecisionService;
import com.cn.zyd.common.auth.service.PermissionSecurityMetadataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;

import javax.servlet.*;
import java.io.IOException;

/**
 * @title: 权限验证前置拦截器
 */
@Slf4j
public class PermissionSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private PermissionSecurityMetadataSource permissionSecurityMetadataSource;

    public PermissionSecurityInterceptor(PermissionSecurityMetadataSource permissionSecurityMetadataSource) {
        this.permissionSecurityMetadataSource = permissionSecurityMetadataSource;
    }

    /**
     * @description 设置自定义鉴权中心
     */
    @Autowired
    public void setAccessDecisionService(AccessDecisionService accessDecisionService) {
        super.setAccessDecisionManager(accessDecisionService);
    }

    /**
     * @description 初始化
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * @description 复写过滤器，加入自定义过滤
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation filterInvocation = new FilterInvocation(servletRequest, servletResponse, filterChain);

        invoke(filterInvocation);
    }

    /**
     * @description 自定义鉴权处理
     */
    private void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        //filterInvocation里面有一个被拦截的url
        //里面调用PermissionSecurityMetadataSource的getAttributes(Object object)这个方法获取filterInvocation的URL对应的所有角色
        //再调用PermissionDecisionManager的decide方法来校验用户的角色是否足够
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        log.debug("自定义鉴权 -- 通过");
        try {
            //执行下一个拦截器
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    /**
     * @description 这里可以加入自定义的销毁流程
     */
    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    /**
     * @description 复写，将自定义鉴权替换为默认鉴权
     */
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.permissionSecurityMetadataSource;
    }
}
