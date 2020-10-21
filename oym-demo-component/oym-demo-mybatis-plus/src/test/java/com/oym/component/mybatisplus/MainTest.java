package com.oym.component.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.cn.component.mybatisplus.dao")
public class MainTest {

    public static void main(String[] args) {
        SpringApplication.run(MainTest.class, args);
    }

}
