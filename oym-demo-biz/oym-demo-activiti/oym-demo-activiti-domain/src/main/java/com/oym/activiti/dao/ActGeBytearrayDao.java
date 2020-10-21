package com.oym.activiti.dao;

import com.oym.activiti.entity.ActGeBytearray;
import com.oym.base.db.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActGeBytearrayDao extends BaseDao<ActGeBytearray> {
    /**
     * 根据部署id查询部署内容
     */
    List<Map<String, Object>> selectByDeploymentId(String deploymentId);

    /**
     * 更新流程信息
     */
    void updateBpmnByDeploymentId(@Param("deploymentId") String deploymentId, @Param("bpmnXml") byte[] bpmnXml);

}
