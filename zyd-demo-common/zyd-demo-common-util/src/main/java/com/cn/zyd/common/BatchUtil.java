package com.cn.zyd.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyd
 * @date 2019/11/25 9:04
 * @desc
 */
public final class BatchUtil {
    private BatchUtil() {
    }

    /**
     * @param list      数据列表
     * @param onceCount 一次操作的数量
     * @desc 分个数取
     */
    public static <T> void batchByOnceCount(List<T> list, int onceCount, Handler<T> handler) {
        if (list == null) {
            list = new ArrayList<>();
        }
        int count = list.size() / onceCount;
        for (int i = 0; i < count; i++) {
            int start = i * onceCount;
            int end = (i + 1) * onceCount;
            List<T> subList = list.subList(start, end);
            handler.onceHandler(i, subList);
        }
        int less = list.size() % onceCount;
        if (less == 0) {
            return;
        }
        int lessStart = count * onceCount;
        int lessEnd = count * onceCount + less;
        List<T> subList = list.subList(lessStart, lessEnd);
        handler.onceHandler(count + 1, subList);
    }

    /**
     * @param list  数据列表
     * @param times 分批次数
     * @desc 分次数取
     */
    public static <T> void batchByTimes(List<T> list, int times, Handler<T> handler) {
        if (list == null) {
            list = new ArrayList<>();
        }
        int onceCount = (list.size() / times) + 1;
        batchByOnceCount(list, onceCount, handler);
    }

    public static interface Handler<T> {
        void onceHandler(int times, List<T> list);
    }

}
