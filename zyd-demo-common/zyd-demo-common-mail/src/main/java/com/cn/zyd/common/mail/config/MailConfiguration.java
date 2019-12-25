package com.cn.zyd.common.mail.config;

import com.cn.zyd.common.mail.MailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zyd
 * @date 2019/9/25 14:59
 * @desc
 */
@Configuration
public class MailConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "spring.mail", name = "host")
    public MailService mailUtil() {
        return new MailService();
    }

}
