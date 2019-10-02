package com.cn.zyd.activiti.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyd
 * @date 2019/9/29 10:08
 * @desc
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {

    }
}
