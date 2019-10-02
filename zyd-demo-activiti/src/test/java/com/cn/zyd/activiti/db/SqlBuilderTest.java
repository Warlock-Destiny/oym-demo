package com.cn.zyd.activiti.db;

import org.junit.Assert;
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
        map.put("id_", 123);
        map.put("user_name", "zyd");
        map.put("create_time", new Date(1569826564013L));
        String sql = SqlBuilder.newInstance("activiti", "t_sat_clearing", entityModel, map).buildInsertSql();
        System.out.println(sql);
        Assert.assertEquals(sql,
                "INSERT INTO activiti.t_sat_clearing(id_,user_name,create_time) VALUES (123,'zyd','2019-09-30 14:56:04 013' );"
        );
    }

    @Test
    public void testDeleteSql() {
        EntityModel entityModel = mockEntityModel();
        Map<String, Object> map = new HashMap<>();
        map.put("id_", 123);
        map.put("user_name", "zyd");
        map.put("create_time", new Date(1569826564013L));
        String sql = SqlBuilder.newInstance("activiti", "t_sat_clearing", entityModel, map).buildDeleteSql();
        System.out.println(sql);
        Assert.assertEquals(sql,
                "DELETE FROM activiti.t_sat_clearing WHERE id_ = 123;"
        );
    }

    private EntityModel mockEntityModel() {
        EntityModel entityModel = new EntityModel();
        List<EntityModel.Field> columns = new ArrayList<>();
        columns.add(new EntityModel.Field(true, "id_", "int"));
        columns.add(new EntityModel.Field(false, "user_name", "varchar"));
        columns.add(new EntityModel.Field(false, "create_time", "datetime"));
        entityModel.setColumns(columns);
        return entityModel;
    }

}

