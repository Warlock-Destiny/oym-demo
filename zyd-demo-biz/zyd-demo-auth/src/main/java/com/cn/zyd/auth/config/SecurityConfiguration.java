package com.cn.zyd.auth.config;

import com.cn.zyd.auth.service.AuthenticationUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * @description 自定义用户鉴权类
     */
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        //通过实现UserDetailService来完成自定义用户授权认证
        return new AuthenticationUserDetailService();
    }

    /**
     * @description SpringBoot 2.x 配置，这里要bean 注入，否则报错
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @description 权限过滤链配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //所有请求都需要进行权限认证
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        //配置登录方法无需授权
        http.formLogin().loginPage("/login").failureUrl("/login?code=").permitAll();
        //配置登出无需授权
        http.logout().permitAll();
        //配置鉴权无需认证
        http.authorizeRequests().antMatchers("/oauth/authorize").permitAll();
    }

    /**
     * @description 启用密码加密功能
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}