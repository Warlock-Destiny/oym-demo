package com.cn.zyd.base.controller;

import com.cn.zyd.base.db.constant.SortEnum;
import com.cn.zyd.base.exception.ParamException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author zyd
 * @date 2019/10/27 4:11
 * @desc 校验功能
 */
public interface ValidFun {

    /**
     * 校验值是否符合规范
     *
     * @param sort 排序字段
     */
    default ValidFun validSort(String sort) {
        if (sort != null && !SortEnum.validSort(sort)) {
            throw new ParamException("sort字段不符合格式:asc/desc" + sort);
        }
        return this;
    }

    /**
     * 校验参数是否符合规范
     *
     * @param bol 表达式的值
     * @param msg 返回信息
     */
    default ValidFun validParam(Boolean bol, String msg) {
        if (bol) {
            throw new ParamException(msg);
        }
        return this;
    }

    default ValidFun validNull(Object o, String field) {
        if (o == null) {
            throw new ParamException(field + "不能为null");
        }
        return this;
    }

    default ValidFun validEmpty(String s, String field) {
        if (StringUtils.isEmpty(s)) {
            throw new ParamException(field + "不能为空");
        }
        return this;
    }

    default ValidFun validBlank(String s, String field) {
        if (StringUtils.isBlank(s)) {
            throw new ParamException(field + "不能为空");
        }
        return this;
    }

    default ValidFun validLength(String s, String field, int minSize, int maxSize) {
        validNull(s, field);
        if (s.trim().length() < minSize) {
            throw new ParamException(field + "长度不能小于" + minSize);
        }
        if (s.trim().length() > maxSize) {
            throw new ParamException(field + "长度不能大于" + maxSize);
        }
        return this;
    }

    default ValidFun validMaxLength(String s, String field, int maxSize) {
        validNull(s, field);
        if (s.trim().length() > maxSize) {
            throw new ParamException(field + "长度不能大于" + maxSize);
        }
        return this;
    }

    /**
     * @param supplier 入参函数
     */
    default ValidFun validParam(Supplier<Boolean> supplier, String msg) {
        return validParam(supplier.get(), msg);
    }

    default <T> ValidFun validCollection(List<T> list, String field) {
        if (list == null || list.isEmpty()) {
            throw new ParamException(field + "不能为空");
        }
        return this;
    }
}
