package com.cn.zyd.common.feign.config;

import com.cn.zyd.common.feign.interceptor.FeignGetRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zyd
 * @date 2020/1/17 11:06
 * @desc
 */
@Configuration
public class CommonFeignConfig {

    @Bean
    public FeignGetRequestInterceptor getInterceptor() {
        return new FeignGetRequestInterceptor();
    }
}
