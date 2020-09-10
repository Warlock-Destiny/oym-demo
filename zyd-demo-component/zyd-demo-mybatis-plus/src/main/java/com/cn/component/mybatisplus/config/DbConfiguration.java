package com.cn.component.mybatisplus.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.cn.component.mybatisplus.method.BatchInsert;
import com.cn.component.mybatisplus.method.UpdateAllById;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author zyd
 * @date 2020/5/15 15:43
 * @desc
 */
@Configuration
@ConditionalOnProperty(value = "component.mybatis-plus", havingValue = "true")
@ConditionalOnClass(MybatisConfiguration.class)
public class DbConfiguration {

    /**
     * 如果使用者需要自主声明便提供出去
     */
    @Bean
    @ConditionalOnMissingBean(ISqlInjector.class)
    public ISqlInjector iSqlInjector() {
        return new AbstractSqlInjector() {
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
                return Stream.of(
                        new Insert(),
                        new Delete(),
                        new DeleteByMap(),
                        new DeleteById(),
                        new DeleteBatchByIds(),
                        new Update(),
                        new UpdateById(),
                        new SelectById(),
                        new SelectBatchByIds(),
                        new SelectByMap(),
                        new SelectOne(),
                        new SelectCount(),
                        new SelectMaps(),
                        new SelectMapsPage(),
                        new SelectObjs(),
                        new SelectList(),
                        new SelectPage(),
                        new UpdateAllById(),
                        new BatchInsert()
                ).collect(toList());
            }
        };
    }

    @Bean
    public GlobalConfig globalConfig(ISqlInjector iSqlInjector) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setSqlInjector(iSqlInjector);
        return globalConfig;
    }

    /**
     * 分页插件使用这个插件能才能使用mybatis-plus的分页功能
     */
    @Bean
    @ConditionalOnMissingBean(PaginationInterceptor.class)
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
