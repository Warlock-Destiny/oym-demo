package com.cn.zyd.demo.web;

import com.cn.zyd.demo.web.bean.TestBean;
import com.cn.zyd.demo.web.config.DemoAutoConfiguration;
import com.cn.zyd.demo.web.config.DemoConfiguration;
import com.cn.zyd.demo.web.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApplication.class, args);
        SpringUtil.getBean(DemoAutoConfiguration.class).say();
    }

    @Bean
    public TestBean getTestBean(){
        return new TestBean();
    }
}
