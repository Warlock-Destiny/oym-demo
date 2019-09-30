package com.cn.zyd.auth.dao;

import com.cn.zyd.auth.entity.ColumnInfo;
import com.cn.zyd.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zyd
 * @date 2019/9/27 8:36
 * @desc
 */
@Repository
public interface ColumnsDao extends BaseDao {

    /**
     * 根据dbName和tableName查找字段名
     *
     * @param db    数据库名
     * @param table 表名
     * @return 字段名
     */
    List<ColumnInfo> selectTableInfoByDbAndTable(String db, String table);
}
