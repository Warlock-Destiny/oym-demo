package com.cn.zyd.redis.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author zhangyd
 * @date 2019/9/2423:24
 * @desc 全局bean获取类
 */
@Component
public class SpringContextHolderUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringContextHolderUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String className) {
        return applicationContext.getBean(className);
    }

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }
}
