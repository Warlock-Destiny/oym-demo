package com.oym.base.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: zhangyd
 * @date: 2020/10/21 11:08
 */
@SpringBootApplication(scanBasePackages = "com.oym.base.db")
@MapperScan("com.oym.base.db")
public class TestDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestDbApplication.class, args);
    }

}

