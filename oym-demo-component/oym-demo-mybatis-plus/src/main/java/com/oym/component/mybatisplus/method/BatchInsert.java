package com.oym.component.mybatisplus.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.oym.component.mybatisplus.entity.BaseEntity;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zyd
 * @date 2020/5/15 15:47
 * @desc 批量插入数据库 插入字段不包括主键和更新时间
 */
public class BatchInsert extends AbstractMethod {

    private static final String METHOD_NAME = "batchInsert";
    private static final String METHOD_SQL = "<script>\nINSERT INTO %s %s VALUES %s\n</script>";

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = String.format(METHOD_SQL, tableInfo.getTableName(), getColumn(tableInfo), getValue(tableInfo));
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return this.addMappedStatement(
                mapperClass,
                METHOD_NAME,
                sqlSource,
                SqlCommandType.INSERT,
                modelClass,
                null,
                null,
                new NoKeyGenerator(),
                null,
                null
        );
    }


    private String getColumn(TableInfo tableInfo) {
        //1 获取插入字段
        List<TableFieldInfo> tableFieldInfoList = tableInfo.getFieldList();
        return LEFT_BRACKET + tableFieldInfoList.stream()
                .map(TableFieldInfo::getColumn)
                .filter(x -> !BaseEntity.UPDATE_TIME.equals(x))
                .collect(Collectors.joining(COMMA)) + RIGHT_BRACKET;
    }

    private String getValue(TableInfo tableInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("<foreach collection=\"list\" item=\"item\" separator=\",\">\n")
                .append("(");
        String middle = tableInfo.getFieldList().stream()
                .filter(x -> !BaseEntity.UPDATE_TIME.equals(x.getColumn()))
                .map(field -> HASH_LEFT_BRACE + "item." + field.getProperty() + RIGHT_BRACE)
                .collect(Collectors.joining(COMMA));
        sb.append(middle);
        sb.append(")\n")
                .append("</foreach>");
        return sb.toString();
    }

}
