package com.cn.zyd.activiti.dao;

import com.cn.zyd.activiti.entity.ActReDeployment;
import com.cn.zyd.base.BaseDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ActReDeploymentDao extends BaseDao<ActReDeployment> {

    Map<String,Object> selectById(String id);
}
