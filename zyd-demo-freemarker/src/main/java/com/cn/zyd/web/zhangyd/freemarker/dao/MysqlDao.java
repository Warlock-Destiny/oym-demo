package com.cn.zyd.web.zhangyd.freemarker.dao;

import com.cn.zyd.web.zhangyd.freemarker.model.ColumnInfo;
import com.cn.zyd.web.zhangyd.freemarker.model.TableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MysqlDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TableInfo getDbInfo(String db, String tablename) {
        return jdbcTemplate.query(
                "select * from information_schema.`TABLES` where TABLE_SCHEMA=? and  TABLE_NAME=?",
                rs -> {
                    rs.next();
                    TableInfo tableInfo = new TableInfo();
                    tableInfo.setTableScheme(rs.getString("TABLE_SCHEMA"));
                    tableInfo.setTableName(rs.getString("TABLE_NAME"));
                    tableInfo.setTableComment(rs.getString("TABLE_COMMENT"));
                    return tableInfo;
                },new String[]{db, tablename});
    }

    public List<ColumnInfo> getColumnInfo(String db, String tableName) {
        String sql = "select * from information_schema.`COLUMNS` where TABLE_SCHEMA=? and TABLE_NAME=?";
        return jdbcTemplate.query(sql, new String[]{db, tableName}, (RowMapper) (resultSet, i) -> {
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setColumnName(resultSet.getString("COLUMN_NAME"));
            columnInfo.setType(resultSet.getString("DATA_TYPE"));
            columnInfo.setComment(resultSet.getString("COLUMN_COMMENT"));
            return columnInfo;
        });
    }
}
