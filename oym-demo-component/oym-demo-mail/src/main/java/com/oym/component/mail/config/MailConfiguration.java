package com.oym.component.mail.config;

import com.oym.component.mail.MailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyd
 * @date 2019/9/25 14:59
 * @desc
 */
@Configuration
public class MailConfiguration {
    @Bean
    @ConditionalOnProperty(value = "component.mail", havingValue = "true")
    public MailService mailUtil() {
        return new MailService();
    }

}
