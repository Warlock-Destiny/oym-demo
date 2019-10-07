package com.cn.zyd.activiti.db;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zyd
 * @date 2019/9/27 8:43
 * @desc sql语句的制作
 */
public class SqlBuilder {
    private String db;
    private String table;
    private EntityModel entityModel;
    //数据库字段对应的值
    private Map<String, Object> columnName2Value;

    private SqlBuilder(String db, String table, EntityModel entityModel, Map<String, Object> columnName2Value) {
        this.db = db;
        this.table = table;
        this.entityModel = entityModel;
        this.columnName2Value = columnName2Value;
    }

    public static SqlBuilder newInstance(String db, String table, EntityModel entityModel, Map<String, Object> columnName2Value) {
        return new SqlBuilder(db, table, entityModel, columnName2Value);
    }

    public String buildInsertSql() {
        if (columnName2Value == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(db).append(".").append(table).append("(");
        List<String> columnList = entityModel.getColumns().stream().map(EntityModel.Field::getColumnName).collect(Collectors.toList());
        sb.append(String.join(",", columnList)).append(")");
        sb.append(" VALUES (");
        List<EntityModel.Field> fieldList = entityModel.getColumns();
        for (int i = 0; i < fieldList.size(); i++) {
            EntityModel.Field field = fieldList.get(i);
            sb.append(generateColumnValue(field.getFieldType(), columnName2Value.get(field.getColumnName())))
                    .append(i == (fieldList.size() - 1) ? "" : ",");
        }
        for (EntityModel.Field field : entityModel.getColumns()) {
            generateColumnValue(field.getFieldType(), columnName2Value.get(field.getColumnName()));
        }
        sb.append(" );");
        return sb.toString();
    }

    public String buildDeleteSql() {
        if (columnName2Value == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        EntityModel.Field field = entityModel.getColumns().stream().filter(EntityModel.Field::isPrimary).findFirst().orElse(null);
        if (field == null) {
            return "";
        }
        sb.append("DELETE FROM ").append(db).append(".").append(table)
                .append(" WHERE ").append(field.getColumnName()).append(" = ")
                .append(generateColumnValue(field.getFieldType(), columnName2Value.get(field.getColumnName()))).append(";");
        return sb.toString();
    }

    /**
     * 根据类型获取真实的值
     */
    private String generateColumnValue(String columnType, Object value) {
        return ColumnTypeEnum.getValue(columnType.toUpperCase(), value);
    }
}
