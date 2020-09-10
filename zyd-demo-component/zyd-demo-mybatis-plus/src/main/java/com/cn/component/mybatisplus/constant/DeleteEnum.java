package com.cn.component.mybatisplus.constant;

/**
 * @author zyd
 * @date 2019/10/28 9:13
 * @desc
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
