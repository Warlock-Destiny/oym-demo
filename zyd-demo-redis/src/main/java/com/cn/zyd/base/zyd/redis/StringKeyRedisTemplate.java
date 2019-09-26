package com.cn.zyd.base.zyd.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zhangyd
 * @date 2019/9/24 23:41
 * @desc redis操作类
 */
public class StringKeyRedisTemplate extends RedisTemplate<String, Object> {

    public StringKeyRedisTemplate() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        this.setKeySerializer(stringSerializer);
        this.setHashKeySerializer(stringSerializer);
    }

    public StringKeyRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

}
