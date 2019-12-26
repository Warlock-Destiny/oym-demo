package com.cn.zyd.common.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zyd
 * @date 2019/12/14 21:42
 * @desc
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers("/login/**","/v2/api-docs","/definitions/**", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui");
        web.ignoring().antMatchers("/**");
    }

}
