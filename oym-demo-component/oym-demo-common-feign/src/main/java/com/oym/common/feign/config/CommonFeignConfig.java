package com.oym.common.feign.config;

import com.oym.common.feign.interceptor.FeignGetRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangyd
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
