package com.oym.component.web.config;

import com.oym.component.web.log.BaseControllerLog;
import com.oym.component.web.log.DefaultControllerLog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zyd
 * @date 2020/5/27 22:07
 * @desc
 */
@Configuration
public class ControllerConfig {

    @Bean
    @ConditionalOnMissingBean(BaseControllerLog.class)
    @ConditionalOnProperty(value = "component.web.default.log", havingValue = "true")
    public DefaultControllerLog defaultControllerLog() {
        return new DefaultControllerLog();
    }
}
