package com.cn.zyd.activiti.db;

import org.junit.Test;

import java.util.*;

/**
 * @author zyd
 * @date 2019/9/27 14:32
 * @desc
 */
public class SqlBuilderTest {
    @Test
    public void testInsertSql() {
        System.out.println(System.currentTimeMillis());
        EntityModel entityModel = mockEntityModel();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 123);
        map.put("dict_field", "part_name");
        map.put("dict_code", "zyd");
        map.put("dict_value", "这样的");
        map.put("dict_desc", "姓名");
        map.put("delete_status", 0);
        map.put("gmt_create", new Date());
        map.put("gmt_modified", new Date());
        String sql = SqlBuilder.newInstance("ctff-cloud", "sys_dict", entityModel, map).buildInsertSql();
        System.out.println(sql);
    }

    @Test
    public void testDeleteSql() {
        System.out.println(System.currentTimeMillis());
        EntityModel entityModel = mockEntityModel();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 123);
        map.put("dict_field", "part_name");
        map.put("dict_code", "zyd");
        map.put("dict_value", "这样的");
        map.put("dict_desc", "姓名");
        map.put("delete_status", 0);
        map.put("gmt_create", new Date());
        map.put("gmt_modified", new Date());
        String sql = SqlBuilder.newInstance("ctff-cloud", "sys_dict", entityModel, map).buildDeleteSql();
        System.out.println(sql);
    }

    private EntityModel mockEntityModel() {
        EntityModel entityModel = new EntityModel();
        List<EntityModel.Field> columns = new ArrayList<>();
        columns.add(new EntityModel.Field(true, "id", "int"));
        columns.add(new EntityModel.Field(false, "dict_field", "varchar"));
        columns.add(new EntityModel.Field(false, "dict_code", "varchar"));
        columns.add(new EntityModel.Field(false, "dict_value", "varchar"));
        columns.add(new EntityModel.Field(false, "dict_desc", "varchar"));
        columns.add(new EntityModel.Field(false, "delete_status", "tinyint"));
        columns.add(new EntityModel.Field(false, "gmt_create", "timestamp"));
        columns.add(new EntityModel.Field(false, "gmt_modified", "datetime"));
        entityModel.setColumns(columns);
        return entityModel;
    }

}
