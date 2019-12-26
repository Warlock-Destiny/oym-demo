package com.cn.zyd.common.auth.config;

import com.cn.zyd.common.auth.interceptor.PermissionSecurityInterceptor;
import com.cn.zyd.common.auth.service.PermissionSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.http.HttpServletResponse;

/**
 * @ EnableWebSecurity 启用Spring Cloud Security Web安全
 */
@Configuration
@EnableResourceServer
@EnableWebSecurity
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private AccessDecisionManager accessDecisionManager;
    @Autowired
    private PermissionSecurityMetadataSource permissionSecurityMetadataSource;

    /**
     * @description 资源保护路径
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //关闭csrf请求，并捕获异常请求，返回401无权限
        http.csrf().disable().exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                //所有的请求，都必须携带auth认证参数
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterAfter(createPermissionSecurityInterceptor(), FilterSecurityInterceptor.class)
                .httpBasic();
    }

    private PermissionSecurityInterceptor createPermissionSecurityInterceptor() {
        PermissionSecurityInterceptor permissionSecurityInterceptor = new PermissionSecurityInterceptor(permissionSecurityMetadataSource);
        permissionSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
        return permissionSecurityInterceptor;
    }
}
