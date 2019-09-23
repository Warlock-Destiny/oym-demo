package com.cn.zyd.demo.web.controller;

import com.cn.zyd.demo.web.bean.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test1Controller {

//    @Value("${nickName}")
//    private String port;
    @Autowired
    private TestBean testBean;

    @RequestMapping("aac")
    public String aa(){
        System.out.println(testBean);
        return "";
    }
}
