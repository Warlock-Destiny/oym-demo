package com.cn.generate.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zyd
 * @date 2020/7/20 10:29
 * @desc
 */
@Data
public class TableModel {
    //库名
    private String tableSchema;
    //表名
    private String tableName;
    //备注
    private String tableComment;
    //创建时间
    private Date createTime;


    //主键
    private ColumnModel pk;
    //去除主键总列表
    private List<ColumnModel> columnModelList;
    //类名(第一个字母大写)，如：sys_user => SysUser
    private String className;
    //类名(第一个字母小写)，如：sys_user => sysUser
    private String classname;
    //dto名称
    private String dtoName;

}
