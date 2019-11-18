package com.cn.zyd.common.util;

import com.cn.zyd.common.util.model.TreeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author zyd
 * @date 2019/11/11 11:30
 * @desc 树生成工具类 可能效率不会很高 复杂度n2
 */
public class TreeUtil {
    /**
     * 确定只有一个父id的时候使用
     *
     * @param pid         父id
     * @param treeModel   返回结果 默认穿null
     * @param bList       数据来源
     * @param pidFunction 获取父id的方法
     * @param b2e         将b转换为返回值
     */
    public static <T, E extends TreeModel<T, E>, B> E buildTree(
            T pid,
            E treeModel,
            List<B> bList,
            Function<B, T> pidFunction,
            Function<B, E> b2e
    ) {
        List<E> eList = buildListTree(pid, treeModel, bList, pidFunction, b2e);
        return eList.isEmpty() ? null : eList.get(0);
    }

    /**
     * 可能有多个父id的时候使用
     *
     * @param pid         父id
     * @param treeModel   返回结果 默认穿null
     * @param bList       数据来源
     * @param pidFunction 获取父id的方法
     * @param b2e         将b转换为返回值
     */
    public static <T, E extends TreeModel<T, E>, B> List<E> buildListTree(
            T pid,
            E treeModel,
            List<B> bList,
            Function<B, T> pidFunction,
            Function<B, E> b2e
    ) {
        List<E> eList = new ArrayList<>();
        if (treeModel == null) {
            for (B b : bList) {
                if (Objects.equals(pid, pidFunction.apply(b))) {
                    E e = b2e.apply(b);
                    eList.add(recuit(e.getId(), e, bList, pidFunction, b2e));
                }
            }
        }
        return eList;
    }

    private static <T, E extends TreeModel<T, E>, B> E recuit(
            T pid,
            E treeModel,
            List<B> bList,
            Function<B, T> function,
            Function<B, E> b2e
    ) {
        for (B b : bList) {
            if (Objects.equals(pid, function.apply(b))) {
                List<E> eList = treeModel.getChildren();
                if (eList == null) {
                    eList = new ArrayList<>();
                    treeModel.setChildren(eList);
                }
                E e = b2e.apply(b);
                eList.add(e);
                recuit(e.getId(), e, bList, function, b2e);
            }
        }
        return treeModel;
    }

}
