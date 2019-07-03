package com.cn.zyd.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class GnwDemoConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(GnwDemoConfigApplication.class, args);
    }

}
