package com.cn.zyd.activiti.dao;

import com.cn.zyd.activiti.entity.ActReProcdef;
import com.cn.zyd.base.db.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ActReProcdefDao extends BaseDao<ActReProcdef> {
    Map<String,Object> selectByDeploymentId(String id);
}
