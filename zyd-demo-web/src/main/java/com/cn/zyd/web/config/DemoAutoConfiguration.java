package com.cn.zyd.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DemoConfiguration.class)
public class DemoAutoConfiguration {
    @Autowired
    private DemoConfiguration demoConfiguration;

    public void say(){
        System.out.println(demoConfiguration);
    }
}
