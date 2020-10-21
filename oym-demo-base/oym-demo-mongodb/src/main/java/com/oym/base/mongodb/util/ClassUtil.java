package com.oym.base.mongodb.util;

import com.oym.base.mongodb.exception.NoSuchParameterTypeException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName: ClassUtil
 * @Description:
 * @author: zhangyd
 * @date: 2020/9/17 14:37
 */
public final class ClassUtil {

    /**
     * 获取类的泛型
     */
    public static Class<?> getActualParameter(Class<?> tClazz, int parameterIndex) throws NoSuchParameterTypeException {
        Type type = tClazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type[] types = pType.getActualTypeArguments();
            if (types.length < parameterIndex) {
                throw new NoSuchParameterTypeException("class parameterType length" + types.length + ",but " + parameterIndex);
            }
            Type clazz = pType.getActualTypeArguments()[parameterIndex];
            return (Class<?>) clazz;
        }
        throw new NoSuchParameterTypeException("cannot find " + tClazz + " actual parameterizedType");
    }

    /**
     * 获取field属性配置
     */
    public static List<PropertyDescriptor> getProperty(Class<?> tClazz) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(tClazz);
        return Arrays.stream(beanInfo.getPropertyDescriptors())
                .filter(x -> !Objects.equals(x.getName(), "class"))
                .collect(Collectors.toList());
    }

}
