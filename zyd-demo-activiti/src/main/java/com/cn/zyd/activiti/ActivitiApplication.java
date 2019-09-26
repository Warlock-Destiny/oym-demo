package com.cn.zyd.activiti;

import com.cn.zyd.common.SpringContextHolder;
import com.cn.zyd.activiti.service.TestZhangydService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
        SpringContextHolder.getBean(TestZhangydService.class).deploy();
    }

}
