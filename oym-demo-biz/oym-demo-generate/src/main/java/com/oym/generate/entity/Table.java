package com.oym.generate.entity;

import com.oym.component.mybatisplus.entity.BaseEmptyEntity;
import lombok.Data;

import java.util.Date;

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
