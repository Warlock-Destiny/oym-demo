package com.oym.activiti.dao;

import com.oym.activiti.entity.ActReDeployment;
import com.oym.base.db.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ActReDeploymentDao extends BaseDao<ActReDeployment> {

    Map<String, Object> selectById(String id);
}
