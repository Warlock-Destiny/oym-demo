package com.oym.activiti.dao;

import com.oym.activiti.entity.ActReProcdef;
import com.oym.base.db.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ActReProcdefDao extends BaseDao<ActReProcdef> {
    Map<String,Object> selectByDeploymentId(String id);
}
