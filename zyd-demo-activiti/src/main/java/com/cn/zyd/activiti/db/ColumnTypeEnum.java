package com.cn.zyd.activiti.db;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author zyd
 * @date 2019/9/27 11:19
 * @desc 字段对应的枚举类及处理方式
 */
public enum ColumnTypeEnum {

    TINYINT("tinyint", ColumnTypeFunction.BASE_TYPE),
    INT("int", ColumnTypeFunction.BASE_TYPE),
    BIGINT("bigint", ColumnTypeFunction.BASE_TYPE),
    VARCHAR("varchar", ColumnTypeFunction.STRING_TYPE),
    LONGBLOB("longblob", ColumnTypeFunction.BLOB_TYPE),
    TIMESTAMP("timestamp", ColumnTypeFunction.DATE_TYPE),
    DATETIME("datetime", ColumnTypeFunction.DATE_TYPE);

    private String columnType;
    private Function<Object, String> function;

    ColumnTypeEnum(String columnType, Function<Object, String> function) {
        this.columnType = columnType;
        this.function = function;
    }

    /**
     * 本类提供的唯一方法 根据类型获取不同的字段处理类型
     */
    public static String getValue(String columnType, Object value) {
        return columnTypeEnumMap.get(columnType.toUpperCase()).function.apply(value);
    }

    private static final Map<String, ColumnTypeEnum> columnTypeEnumMap;

    static {
        Map<String, ColumnTypeEnum> map = new HashMap<>();
        for (ColumnTypeEnum columnTypeEnum : ColumnTypeEnum.values()) {
            map.put(columnTypeEnum.columnType.toUpperCase(), columnTypeEnum);
        }
        columnTypeEnumMap = Collections.unmodifiableMap(map);
    }

    /**
     * 根据不同字段类型处理成不同的字符串
     */
    private static class ColumnTypeFunction {

        //基本类型数据
        static final Function<Object, String> BASE_TYPE = s -> s == null ? null : s.toString();
        //字符串类型
        static final Function<Object, String> STRING_TYPE = s -> s == null ? null : "'" + s + "'";
        //日期类型
        static final Function<Object, String> DATE_TYPE = s -> {
            if (s instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                return "'" + sdf.format(s) + "'";
            }
            return s == null ? null : s.toString();
        };
        static final Function<Object, String> BLOB_TYPE = s -> {
            byte[] bytes = (byte[]) s;
            StringBuilder sb = new StringBuilder();
            sb.append("0x");
            for (byte aByte : bytes) {
                String hex = Integer.toHexString(aByte & 0xFF);
                if (hex.length() < 2) {
                    sb.append(0);
                }
                sb.append(hex.toUpperCase());
            }
            return sb.toString();
        };

    }
}
