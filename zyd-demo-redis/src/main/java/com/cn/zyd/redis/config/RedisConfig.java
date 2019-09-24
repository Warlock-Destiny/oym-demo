package com.cn.zyd.redis.config;

import com.cn.zyd.redis.StringKeyRedisTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;


/**
 * @author zhangyd
 * @date 2019/9/25 0:51
 * @desc
 */
@Component
public class RedisConfig {
    @Bean
    @ConditionalOnMissingBean
    public StringKeyRedisTemplate StringKeyRedisTemplate(RedisConnectionFactory redisConnectionFactory)  {
        StringKeyRedisTemplate template = new StringKeyRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
