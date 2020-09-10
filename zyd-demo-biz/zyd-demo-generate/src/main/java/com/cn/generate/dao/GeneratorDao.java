package com.cn.generate.dao;

import com.cn.component.mybatisplus.BaseDao;
import com.cn.component.mybatisplus.entity.BaseEntity;
import com.cn.generate.base.query.TableQuery;
import com.cn.generate.entity.Column;
import com.cn.generate.entity.Table;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@MapperScan
public interface GeneratorDao extends BaseDao<BaseEntity> {

    List<Table> queryTableList(TableQuery tableQuery);

    List<Column> queryColumns(TableQuery tableQuery);
}
