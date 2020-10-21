package com.oym.generate.model;

import lombok.Data;

/**
 * @author zyd
 * @date 2020/7/20 10:30
 * @desc
 */
@Data
public class ColumnModel {
    //主键
    private String columnKey;
    //列名
    private String columnName;
    //列名类型
    private String dataType;
    //列名备注
    private String columnComment;

    //属性名称(第一个字母大写)，如：user_name => UserName
    private String attrName;
    //属性名称(第一个字母小写)，如：user_name => userName
    private String attrname;
    //属性类型
    private String attrType;
    //auto_increment
    private String extra;
    //全大写 如：user_name => USER_NAME
    private String topName;

    //实体类中是否展示
    private Boolean entityShow;
    //dto中是否展示
    private Boolean dtoShow;
    //batchinsert中是否展示
    private Boolean batchInsertShow;
}
