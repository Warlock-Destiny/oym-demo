package com.cn.zyd.activiti;

import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cn")
@MapperScan("com.cn.zyd.activiti.dao")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Test.class, args);
    }
}
