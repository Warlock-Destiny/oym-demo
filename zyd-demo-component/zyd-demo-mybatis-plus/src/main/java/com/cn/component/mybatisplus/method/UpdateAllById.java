package com.cn.component.mybatisplus.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.cn.component.mybatisplus.entity.BaseEntity;
import com.google.common.collect.ImmutableList;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

/**
 * @author zyd
 * @date 2020/3/11 8:37
 * @desc
 */
public class UpdateAllById extends AbstractMethod {

    private static final String METHOD_NAME = "updateAllById";
    private static final String METHOD_SQL = "<script>\nUPDATE %s %s WHERE %s=#{%s} %s\n</script>";
    private static final List<String> IGNORE_UPDATE_FIELD = ImmutableList.of(BaseEntity.CREATE_TIME, BaseEntity.UPDATE_TIME);

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        final String additional = optlockVersion() + tableInfo.getLogicDeleteSql(true, false);
        String sql = String.format(METHOD_SQL, tableInfo.getTableName(),
                generateSet(tableInfo),
                tableInfo.getKeyColumn(), ENTITY_DOT + tableInfo.getKeyProperty(), additional);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, METHOD_NAME, sqlSource);
    }

    private String generateSet(TableInfo tableInfo) {
        return "set " + getAllSqlSet(tableInfo);
    }

    private String getAllSqlSet(TableInfo tableInfo) {
        return tableInfo.getFieldList().stream()
                .filter(i -> {
                    if (i.isLogicDelete()) {
                        return !(tableInfo.isLogicDelete() && i.isLogicDelete());
                    }
                    return true;
                }).map(this::getSqlSet).filter(Objects::nonNull).collect(joining(COMMA + NEWLINE));
    }

    public String getSqlSet(TableFieldInfo tableFieldInfo) {
        String column = tableFieldInfo.getColumn();
        String update = tableFieldInfo.getUpdate();
        String el = tableFieldInfo.getEl();
        String sqlSet = column + EQUALS;
        if (column != null && (IGNORE_UPDATE_FIELD.contains(column))) {
            return null;
        }
        if (StringUtils.isNotEmpty(update)) {
            sqlSet += String.format(update, column);
        } else {
            sqlSet += SqlScriptUtils.safeParam(ENTITY_DOT + el);
        }
        return sqlSet;
    }
}
