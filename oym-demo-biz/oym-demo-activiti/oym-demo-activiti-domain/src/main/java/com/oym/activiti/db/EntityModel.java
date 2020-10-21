package com.oym.activiti.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zhangyd
 * @date 2019/9/27 8:23
 * @desc 实体类model
 */
@Data
@Accessors(chain = true)
public class EntityModel {

    private List<Field> columns;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class Field {
        private boolean primary;
        // 数据库字段名
        private String columnName;
        // 字段类型(数据库字段名) 全大写
        private String fieldType;
    }
}
