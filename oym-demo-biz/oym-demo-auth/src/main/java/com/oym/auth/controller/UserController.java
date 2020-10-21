package com.oym.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyd
 * @date 2019/9/28 22:33
 * @desc
 */
@RestController
@RequestMapping(value = "/")
@Slf4j
public class UserController {

    /**
     * @description 资源服务器可通过请求user接口essToken对应的用户信息
     */
    @GetMapping(value = "user")
    public Authentication user() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}