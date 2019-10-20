package com.cn.zyd.base.db.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.cn.zyd.base.db.injector.CustSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyd
 * @date 2019/10/20 18:00
 * @desc
 */
@Configuration
public class DbConfiguration {

    @Bean
    public CustSqlInjector custSqlInjector() {
        return new CustSqlInjector();
    }

    @Bean
    public GlobalConfig globalConfig(CustSqlInjector custSqlInjector) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setSqlInjector(custSqlInjector);
        return globalConfig;
    }
}
