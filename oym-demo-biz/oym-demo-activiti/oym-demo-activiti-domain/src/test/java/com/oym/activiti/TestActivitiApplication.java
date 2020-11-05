package com.oym.activiti;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: zhangyd
 * @date: 2020/11/05 14:54
 */
@SpringBootApplication(scanBasePackages = "com.oym")
@MapperScan("com.oym.*.dao")
public class TestActivitiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestActivitiApplication.class, args);
    }
}
