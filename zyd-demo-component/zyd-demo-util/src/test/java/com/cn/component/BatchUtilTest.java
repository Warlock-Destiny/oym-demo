package com.cn.component;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zyd
 * @date 2019/11/25 9:05
 * @desc
 */
public class BatchUtilTest {
    @Test
    public void test(){
        List<Integer> list = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.toList());
        //主逻辑 可以不在乎里面的分批逻辑
        BatchUtil.batchByOnceCount(list, 3, (x, y) -> {
            System.out.println("第" + x + "批次" + "数据" + y);
        });
        System.out.println("中途休息一下!");
        List<Integer> list2 = new ArrayList<>();
        BatchUtil.batchByOnceCount(list2, 3, (x, y) -> {
            System.out.println("第" + x + "批次" + "数据" + y);
        });
        List<Integer> list3 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).collect(Collectors.toList());
        //主逻辑 可以不在乎里面的分批逻辑
        BatchUtil.batchByTimes(list3, 2, (x, y) -> {
            System.out.println("第" + x + "批次" + "数据" + y);
        });
    }
}
