package com.cn.zyd.redis.web.zhangyd.freemarker.model;

import java.util.List;

public class TableInfo {
    private String tableScheme;
    private String tableName;
    private String tableComment;
    private List<ColumnInfo> columnInfoList;

    public String getTableScheme() {
        return tableScheme;
    }

    public void setTableScheme(String tableScheme) {
        this.tableScheme = tableScheme;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<ColumnInfo> getColumnInfoList() {
        return columnInfoList;
    }

    public void setColumnInfoList(List<ColumnInfo> columnInfoList) {
        this.columnInfoList = columnInfoList;
    }

}
