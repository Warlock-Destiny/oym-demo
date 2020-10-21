//package com.oym.common;
//
//
//import com.oym.common.util.model.TreeModel;
//import org.springframework.util.CollectionUtils;
//
//import java.util.*;
//import java.util.function.Function;
//
///**
// * @author zhangyd
// * @date 2019/11/11 11:30
// * @desc 树生成工具类 可能效率不会很高 复杂度n2
// */
//public class TreeUtil2 {
//    /**
//     * 确定只有一个父id的时候使用
//     *
//     * @param bList       数据来源
//     * @param pidFunction 获取父id的方法
//     * @param b2e         将b转换为返回值
//     */
//    public static <T, E extends TreeModel<T, E>, B> E buildTree(
//            List<B> bList,
//            Function<B, T> pidFunction,
//            Function<B, E> b2e
//    ) {
//        List<E> eList = buildListTree(bList, pidFunction, b2e);
//        return eList.isEmpty() ? null : eList.get(0);
//    }
//
//
//    /**
//     * 可能有多个父id的时候使用
//     *
//     * @param bList       数据来源
//     * @param pidFunction 获取父id的方法
//     * @param b2e         将b转换为返回值
//     */
//    public static <T, E extends TreeModel<T, E>, B> List<E> buildListTree(
//            List<B> bList,
//            Function<B, T> pidFunction,
//            Function<B, E> b2e
//    ) {
//        Map<T, List<E>> map = new HashMap<>();
//        for (B b : bList) {
//            map.computeIfAbsent(pidFunction.apply(b), k -> new ArrayList<>()).add(b2e.apply(b));
//        }
//        //获取pid
//        Set<T> idSet = new HashSet<>();
//        map.forEach((x, y) -> {
//            y.forEach(z -> {
//                T id = z.getId();
//                idSet.add(id);
//                List<E> testTreeList = map.get(id);
//                if (CollectionUtils.isEmpty(testTreeList)) {
//                    return;
//                }
//                z.setChildren(testTreeList);
//            });
//        });
//        // 剩下的都是最顶层的pid
//        Set<T> topPidTSet = map.keySet();
//        topPidTSet.removeAll(idSet);
//        List<E> eList = new ArrayList<>();
//        map.forEach((x, y) -> {
//            if (topPidTSet.contains(x)) {
//                eList.addAll(y);
//            }
//        });
//        return eList;
//    }
//
//
//}
