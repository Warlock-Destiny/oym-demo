package com.oym.base.db.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

/**
 * @author zhangyd
 * @date 2019/10/20 17:54
 * @desc
 */
public class Demo extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "SELECT %s FROM %s";
        SqlSource sqlSource = new RawSqlSource(configuration, String.format(sql,
                sqlSelectColumns(tableInfo, false),
                tableInfo.getTableName()), Object.class);
        return this.addSelectMappedStatementForTable(mapperClass, "demo", sqlSource, tableInfo);
    }
}
