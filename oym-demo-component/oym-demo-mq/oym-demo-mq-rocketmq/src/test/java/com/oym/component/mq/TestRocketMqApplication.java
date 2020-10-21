package com.oym.component.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangyd
 * @date 2019/11/21 14:31
 * @desc
 */
@SpringBootApplication(scanBasePackages = "com.oym.component.mq")
public class TestRocketMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestRocketMqApplication.class, args);
    }
}
