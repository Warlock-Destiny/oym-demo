package com.cn.zyd.base.db.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyd
 * @date 2019/10/20 18:00
 * @desc
 */
@Configuration
@ConditionalOnClass(MybatisConfiguration.class)
public class DbConfiguration {

    /**
     * 如果使用者需要自主声明便提供出去
     */
    @Bean
    @ConditionalOnMissingBean(ISqlInjector.class)
    public ISqlInjector iSqlInjector() {
        return new DefaultSqlInjector();
    }

    @Bean
    public GlobalConfig globalConfig(ISqlInjector iSqlInjector) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setSqlInjector(iSqlInjector);
        return globalConfig;
    }
}
