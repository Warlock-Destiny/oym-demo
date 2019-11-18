package com.cn.zyd.common.config;

import com.cn.zyd.common.spring.SpringContextHolder;
import com.cn.zyd.common.mail.MailUtil;
import com.cn.zyd.common.redis.RedisUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zyd
 * @date 2019/9/25 14:59
 * @desc
 */
@Configuration
public class CommonConfiguration {
    @Bean
    public SpringContextHolder initSpringContextHolder() {
        return new SpringContextHolder();
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.mail")
    public MailUtil mailUtil() {
        return new MailUtil();
    }

    @Configuration
    @EnableConfigurationProperties(RedisProperties.class)
    static class RedisConfiguration {

        @Bean
        @ConditionalOnBean(RedisTemplate.class)
        public RedisUtil redisUtil() {
            return new RedisUtil();
        }

        @Bean
        @ConditionalOnBean(RedisConnectionFactory.class)
        @ConditionalOnMissingBean(RedisTemplate.class)
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
            RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
            return redisTemplate;
        }
    }

}
