package com.cn.generate.entity;

import com.cn.component.mybatisplus.entity.BaseEmptyEntity;
import com.cn.component.mybatisplus.entity.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Table extends BaseEmptyEntity {
    //库名
    private String tableSchema;
    //表名
    private String tableName;
    //备注
    private String tableComment;
    //创建时间
    private Date createTime;

}
