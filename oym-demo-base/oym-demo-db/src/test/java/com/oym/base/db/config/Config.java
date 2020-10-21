package com.oym.base.db.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.oym.base.db.method.Demo;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: zhangyd
 * @date: 2020/10/21 10:17
 */
@Configuration
public class Config {
    @Bean
    @ConditionalOnMissingBean(ISqlInjector.class)
    public ISqlInjector iSqlInjector() {
        return new AbstractSqlInjector() {
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
                return Stream.of(new Demo()).collect(Collectors.toList());
            }
        };
    }
}
