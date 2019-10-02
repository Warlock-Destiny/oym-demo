package com.cn.zyd.activiti.dao;

import com.cn.zyd.activiti.entity.ActGeBytearray;
import com.cn.zyd.base.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActGeBytearrayDao extends BaseDao<ActGeBytearray> {
    List<Map<String, Object>> selectByDeploymentId(String deploymentId);
}
