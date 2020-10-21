package com.oym.base.db.constant;

/**
 * @author zhangyd
 * @date 2019/10/28 9:13
 * @desc 如果数据有删除字段默认用这个字段格式
 */
public enum DeleteEnum {
    NO_DELETE(0, "未删除"),
    DELETED(1, "已删除");

    public static final String DELETE_COLUMN_FIELD = "delete_status";

    private int code;
    private String value;

    DeleteEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
