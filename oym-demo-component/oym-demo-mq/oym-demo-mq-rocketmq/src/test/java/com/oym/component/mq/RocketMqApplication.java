package com.oym.component.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangyd
 * @date 2019/11/21 14:31
 * @desc
 */
@SpringBootApplication(scanBasePackages = "com.cn.mq")
public class RocketMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RocketMqApplication.class, args);
    }
}
