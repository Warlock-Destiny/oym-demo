package com.cn.zyd.auth.dao;

import com.cn.zyd.auth.entity.ActReDeployment;
import com.cn.zyd.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ActReDeploymentDao extends BaseDao<ActReDeployment> {

    Map<String,Object> selectById(String id);
}
