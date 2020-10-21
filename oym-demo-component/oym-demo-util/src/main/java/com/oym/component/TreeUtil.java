package com.oym.component;

import com.oym.component.util.model.TreeModel;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zyd
 * @date 2019/11/11 11:30
 * @desc 树生成工具类 可能效率不会很高 复杂度n2
 */
public final class TreeUtil {

    /**
     * 无需指定pSign
     *
     * @param bList         数据来源
     * @param pSignFunction 获取父Sign的方法
     * @param b2e           将b转换为返回值
     */
    public static <T, E extends TreeModel<T, E>, B> E buildTree(
            List<B> bList,
            Function<B, T> pSignFunction,
            Function<B, E> b2e
    ) {
        List<E> eList = buildListTree(bList, pSignFunction, b2e);
        return eList.isEmpty() ? null : eList.get(0);
    }

    /**
     * 无需指定pSign
     *
     * @param bList         数据来源
     * @param pSignFunction 获取父Sign的方法
     * @param b2e           将b转换为返回值
     */
    public static <T, E extends TreeModel<T, E>, B> List<E> buildListTree(
            List<B> bList,
            Function<B, T> pSignFunction,
            Function<B, E> b2e
    ) {
        Map<T, List<E>> map = new HashMap<>();
        for (B b : bList) {
            map.computeIfAbsent(pSignFunction.apply(b), k -> new ArrayList<>()).add(b2e.apply(b));
        }
        Set<T> signSet = new HashSet<>();
        map.forEach((x, y) -> y.forEach(z -> {
            T sign = z.getSign();
            signSet.add(sign);
            List<E> testTreeList = map.get(sign);
            if (CollectionUtils.isEmpty(testTreeList)) {
                return;
            }
            z.setChildren(testTreeList);
        }));
        // 剩下的都是最顶层的pSign
        Set<T> topPSignSet = map.keySet();
        topPSignSet.removeAll(signSet);
        List<E> eList = new ArrayList<>();
        map.forEach((x, y) -> {
            if (topPSignSet.contains(x)) {
                eList.addAll(y);
            }
        });
        return eList;
    }

    /**
     * 指定父Sign
     *
     * @param bList         数据来源
     * @param pSignFunction 获取父sign的方法
     * @param b2e           将b转换为返回值
     */
    public static <T, E extends TreeModel<T, E>, B> E buildTree(
            T pSign,
            List<B> bList,
            Function<B, T> pSignFunction,
            Function<B, E> b2e
    ) {
        List<E> eList = buildListTree(pSign, bList, pSignFunction, b2e);
        return eList.isEmpty() ? null : eList.get(0);
    }


    /**
     * 可能有多个父sign的时候使用
     *
     * @param bList         数据来源
     * @param pSignFunction 获取父sign的方法
     * @param b2e           将b转换为返回值
     */
    public static <T, E extends TreeModel<T, E>, B> List<E> buildListTree(
            T pSign,
            List<B> bList,
            Function<B, T> pSignFunction,
            Function<B, E> b2e
    ) {
        Map<T, List<E>> map = new HashMap<>();
        for (B b : bList) {
            map.computeIfAbsent(pSignFunction.apply(b), k -> new ArrayList<>()).add(b2e.apply(b));
        }
        //获取pSign
        List<E> eList = map.get(pSign);
        map.forEach((x, y) -> y.forEach(z -> {
            T sign = z.getSign();
            List<E> testTreeList = map.get(sign);
            if (CollectionUtils.isEmpty(testTreeList)) {
                return;
            }
            z.setChildren(testTreeList);
        }));
        return eList;
    }


    /**
     * 可能有多个父sign的时候使用
     *
     * @param bList         数据来源
     * @param pSignFunction 获取父sign的方法
     * @param b2e           将b转换为返回值
     */
    public static <T, E extends TreeModel<T, E>, B> List<E> buildListTree2(
            T pSign,
            List<B> bList,
            Function<B, T> pSignFunction,
            BiFunction<B, B, E> b2e
    ) {
        Map<T, List<E>> map = new HashMap<>();
        Map<T, B> sign2Map = new HashMap<>();
        for (B b : bList) {
            E e = b2e.apply(b, null);
            sign2Map.put(e.getSign(), b);
            map.computeIfAbsent(pSignFunction.apply(b), k -> new ArrayList<>()).add(e);
        }
        //获取pSign
        List<E> eList = map.get(pSign);
        map.forEach((x, y) -> y.forEach(z -> {
            T sign = z.getSign();
            B nowB = sign2Map.get(sign);
            B pb = sign2Map.get(x);
            List<E> testTreeList = map.get(sign);
            if (CollectionUtils.isEmpty(testTreeList)) {
                return;
            }
            testTreeList.forEach(f->{
                b2e.apply(nowB, pb);
            });
            testTreeList = testTreeList.stream()
                    .map(f -> b2e.apply(nowB, pb))
                    .collect(Collectors.toList());
            z.setChildren(testTreeList);
        }));
        return eList;
    }

}
