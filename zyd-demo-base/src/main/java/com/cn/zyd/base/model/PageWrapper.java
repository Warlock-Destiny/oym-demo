package com.cn.zyd.base.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author zyd
 * @date 2019/10/23 8:37
 * @desc 分页包装类
 */
@Data
@Accessors(chain = true)
public class PageWrapper<T> implements Serializable {
    private long total;
    private List<T> data;

    @SuppressWarnings("rawtypes")
    private static  final PageWrapper EMPTY = new PageWrapper<>().setTotal(0).setData(Collections.emptyList());

    public static <T> PageWrapper<T> data(long total, List<T> t) {
        return new PageWrapper<T>().setTotal(total).setData(t);
    }

    @SuppressWarnings("unchecked")
    public static <T> PageWrapper<T> empty() {
        return (PageWrapper<T>)EMPTY;
    }

}
