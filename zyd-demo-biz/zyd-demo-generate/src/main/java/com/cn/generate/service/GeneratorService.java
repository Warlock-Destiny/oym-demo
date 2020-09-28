

package com.cn.generate.service;

import com.cn.generate.base.query.TableQuery;
import com.cn.generate.dao.GeneratorDao;
import com.cn.generate.entity.Column;
import com.cn.generate.entity.Table;
import com.cn.generate.model.ColumnModel;
import com.cn.generate.model.TableModel;
import com.cn.generate.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;

@Service
public class GeneratorService {
    @Autowired
    private GeneratorDao generatorDao;

    public byte[] generatorCode(String tableSchema, String packName, String moduleName, String[] tableNames) {
        TableQuery tableQuery = new TableQuery()
                .setTableSchema(tableSchema)
                .setTableNameList(Arrays.asList(tableNames));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        List<Table> tableList = generatorDao.queryTableList((tableQuery));
        List<Column> columnList = generatorDao.queryColumns(tableQuery);
        List<TableModel> tableModelList = dealTableData(tableList, columnList);
        for (Table table : tableList) {
            GenUtils.generatorCode(packName, moduleName, table, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    private List<TableModel> dealTableData(List<Table> tableList, List<Column> columnList) {
        Map<String, List<Column>> columnMap = columnList.stream().collect(Collectors.groupingBy(x -> getKey(x.getTableSchema(), x.getTableName()), Collectors.toList()));
        return tableList.stream().map(table -> {
            String key = getKey(table.getTableSchema(), table.getTableName());
            List<Column> tableColumnList = columnMap.get(key);
            if (tableColumnList == null || tableColumnList.isEmpty()) {
                return null;
            }
            TableModel tableModel = convertTable(table);
            for (Column column : tableColumnList) {
                ColumnModel columnModel = convertColumn(column);
                if (StringUtils.equals(column.getColumnKey(), "PRI")) {
                    tableModel.setPk(columnModel);
                } else {
                    tableModel.getColumnModelList().add(columnModel);
                }
            }
            return tableModel;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private TableModel convertTable(Table table) {
        TableModel tableModel = new TableModel();
        tableModel.setTableSchema(table.getTableSchema());
        tableModel.setTableName(table.getTableName());
        tableModel.setColumnModelList(new ArrayList<>());
        return tableModel;
    }

    private ColumnModel convertColumn(Column column) {
        ColumnModel columnModel = new ColumnModel();
        columnModel.setColumnKey(column.getColumnKey());
        columnModel.setColumnName(column.getColumnName());
        columnModel.setDataType(column.getColumnName());
        columnModel.setAttrType(column.getDataType());
        columnModel.setColumnComment(column.getColumnComment());
        return columnModel;
    }


    private String getKey(String tableScheme, String tableName) {
        return tableScheme + "-" + tableName;
    }
}
