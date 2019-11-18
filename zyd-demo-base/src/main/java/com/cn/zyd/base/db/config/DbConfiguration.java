package com.cn.zyd.base.db.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyd
 * @date 2019/10/20 18:00
 * @desc
 */
@Configuration
public class DbConfiguration {

    /**
     * 如果使用者需要自主声明便提供出去
     */
    @Bean
    @ConditionalOnBean(AbstractSqlInjector.class)
    public AbstractSqlInjector custSqlInjector() {
        return new DefaultSqlInjector();
    }

    @Bean
    public GlobalConfig globalConfig(AbstractSqlInjector abstractSqlInjector) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setSqlInjector(abstractSqlInjector);
        return globalConfig;
    }
}
