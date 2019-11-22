package com.cn.zyd.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zyd
 * @date 2019/11/21 14:31
 * @desc
 */
@SpringBootApplication
@ComponentScan("com.cn.zyd.mq")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
