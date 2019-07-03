package com.cn.zyd.demo.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${nickName}")
    private String port;

    @RequestMapping("aa")
    public String aa(){
        System.out.println(port);
        return port;
    }
}
