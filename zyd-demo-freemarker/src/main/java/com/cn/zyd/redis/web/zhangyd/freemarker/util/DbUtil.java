package com.cn.zyd.redis.web.zhangyd.freemarker.util;

import java.util.HashMap;
import java.util.Map;

public class DbUtil {

    private static final Map<String, String> db2JavaTypeMap = new HashMap<>();

    static {
        db2JavaTypeMap.put("tinyint", "Integer");
        db2JavaTypeMap.put("smallint", "Integer");
        db2JavaTypeMap.put("int", "Integer");
        db2JavaTypeMap.put("bigint", "Integer");
        db2JavaTypeMap.put("varchar", "String");
        db2JavaTypeMap.put("datetime", "TimeStamp");
        db2JavaTypeMap.put("timestamp", "TimeStamp");
        db2JavaTypeMap.put("decimal", "BigDecimal");
    }

    public static String getjavaType(String dbType) {
        return db2JavaTypeMap.get(dbType);
    }

    public static String db2Up(String tableName) {
        //t_syn_order_info
        int index = tableName.indexOf("_", 3) + 1;
        String[] array = tableName.substring(index).split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            String one = array[i];
                sb.append(first2Up(one));
        }
        return sb.toString();
    }

    public static String column2UpWrite(String columnName) {
        String[] array = columnName.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            String one = array[i];
            if (i == 0) {
                sb.append(one);
            } else {
                sb.append(first2Up(one));
            }
        }
        return sb.toString();
    }

    private static String first2Up(String name) {;
        String first = name.substring(0, 1).toUpperCase();
        return first + name.substring(1);
    }

    public static void main(String[] args) {
        System.out.println(column2UpWrite("order_finish_time"));

        System.out.println(db2Up("t_syn_order_info"));
    }
}
