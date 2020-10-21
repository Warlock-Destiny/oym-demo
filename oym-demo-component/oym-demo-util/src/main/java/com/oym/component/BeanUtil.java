package com.oym.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangyd
 * @date 2019/10/23 14:37
 * @desc 对象拷贝
 */
@Slf4j
public class BeanUtil extends BeanUtils {

    /**
     * @param s source
     * @param t target
     * @return target
     */
    public static <S, T> T copy(S s, T t) {
        if (s == null) {
            return null;
        }
        if (s instanceof Map) {
            copyMap((Map<String, Object>) s, t);
        } else {
            BeanUtils.copyProperties(s, t);
        }
        return t;
    }

    /**
     * @param s      source
     * @param tClass target 类型
     * @return target
     */
    public static <S, T> T copy(S s, Class<T> tClass) {
        if (s == null) {
            return null;
        }
        T t = null;
        try {
            t = tClass.newInstance();
            if (s instanceof Map) {
                copyMap((Map<String, Object>) s, t);
            } else {
                BeanUtils.copyProperties(s, t);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("拷贝错误", e);
        }
        return t;
    }

    /**
     * list拷贝
     */
    public static <S, T> List<T> copyList(List<S> sList, Class<T> tClass) {
        if (sList == null) {
            return null;
        }
        if (sList.isEmpty()) {
            return Collections.emptyList();
        }
        return sList.stream().map(s -> copy(s, tClass)).collect(Collectors.toList());
    }

    private static <T> void copyMap(Map<String, Object> source, T target) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null) {
                try {
                    Object value = source.get(targetPd.getName());
                    if (value == null) {
                        continue;
                    }
                    if (!ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], value.getClass())) {
                        continue;
                    }
                    if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                        writeMethod.setAccessible(true);
                    }
                    writeMethod.invoke(target, value);
                } catch (Throwable ex) {
                    throw new FatalBeanException(
                            "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                }
            }
        }
    }

}

