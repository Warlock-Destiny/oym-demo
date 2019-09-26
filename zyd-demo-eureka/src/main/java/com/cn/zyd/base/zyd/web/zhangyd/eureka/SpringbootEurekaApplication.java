package com.cn.zyd.base.zyd.web.zhangyd.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringbootEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEurekaApplication.class, args);
    }

}
