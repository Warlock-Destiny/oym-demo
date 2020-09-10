package com.cn.generate;

import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zyd
 * @date 2020/3/30 14:38
 * @desc
 */
@SpringBootApplication
@MapperScan("com.cn.generate.dao")
public class MainTest {

    public static void main(String[] args) {
        SpringApplication.run(Test.class, args);
    }
}
