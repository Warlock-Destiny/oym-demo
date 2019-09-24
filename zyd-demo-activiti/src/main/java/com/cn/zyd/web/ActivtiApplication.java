package com.cn.zyd.web;

import com.cn.zyd.web.service.TestZhangydService;
import com.cn.zyd.util.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cn.dao")
public class ActivtiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivtiApplication.class, args);
        TestZhangydService testZhangydService = SpringContextHolder.getBean(TestZhangydService.class);
        testZhangydService.test();
    }


}