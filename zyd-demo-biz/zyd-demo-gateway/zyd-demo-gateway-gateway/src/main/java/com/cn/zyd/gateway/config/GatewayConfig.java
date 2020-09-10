package com.cn.zyd.gateway.config;

import com.cn.zyd.gateway.handler.GlobalErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @author zyd
 * @date 2019/12/27 16:19
 * @desc
 */
@Configuration
@Slf4j
public class GatewayConfig {
    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(
            ObjectProvider<List<ViewResolver>> viewResolversProvider,
            ServerCodecConfigurer serverCodecConfigurer
    ) {
        GlobalErrorHandler globalErrorHandler = new GlobalErrorHandler();
        globalErrorHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        globalErrorHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        globalErrorHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return globalErrorHandler;
    }
}
