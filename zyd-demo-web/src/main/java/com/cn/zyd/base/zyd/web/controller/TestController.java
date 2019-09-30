package com.cn.zyd.base.zyd.web.controller;

import com.cn.zyd.common.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("aa")
    public String aa() {
        redisUtil.set("cc","dd");
        System.out.println(redisUtil.get("cc"));
        return "aa";
    }
}
