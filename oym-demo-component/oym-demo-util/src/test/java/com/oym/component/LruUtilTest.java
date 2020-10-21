package com.oym.component;

import org.junit.Test;

/**
 * @author zyd
 * @date 2019/11/15 10:21
 * @desc 最近访问最常使用算法
 */
public class LruUtilTest {

    @Test
    public void test(){
        LruUtil<Integer, Integer> lruUtil = new LruUtil<>(3);
        lruUtil.set(1, 1);
        lruUtil.set(2, 2);
        lruUtil.set(3, 3);
        lruUtil.set(4, 4);
        System.out.println(lruUtil.get(1));
        System.out.println(lruUtil.get(2));
        lruUtil.set(5, 5);
        System.out.println(lruUtil.get(2));
        System.out.println(lruUtil.get(3));
    }

}
