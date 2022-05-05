package com.oym.component.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyd
 * @date 2019/11/21 15:15
 * @desc
 */
@Configuration
public class TestConfig {

    @Bean
    public Binding binding() {
        Queue queue = new Queue("bdp-exchange-danger");
        return BindingBuilder.bind(queue).to(new FanoutExchange("zyd.fanout"));
    }

}

