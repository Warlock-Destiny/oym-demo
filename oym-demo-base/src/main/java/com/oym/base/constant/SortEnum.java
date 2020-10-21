package com.oym.base.constant;

/**
 * @author zhangyd
 * @date 2019/11/5 15:43
 * @desc
 */

import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 排序字段枚举类
 */
public enum SortEnum {
    ASC("asc"),
    DESC("desc");
    private String symbol;
    private static final Set<String> SORT_SET;

    public static boolean validSort(String type) {
        return contain(type);
    }

    static {
        SORT_SET = ImmutableSet.copyOf(Arrays.stream(SortEnum.values()).map(SortEnum::getSymbol).collect(Collectors.toList()));
    }

    SortEnum(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    private static boolean contain(String type) {
        return SORT_SET.contains(type);
    }

    public static boolean isAsc(String symbol) {
        return Objects.equals(symbol, ASC.getSymbol());
    }
}
