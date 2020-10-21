package com.oym.activiti.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zyd
 * @date 2019/9/27 16:52
 * @desc
 */
@Data
@Accessors(chain = true)
public class ColumnInfo {
    private String columnName;
    private String dataType;
    private String columnKey;
}
