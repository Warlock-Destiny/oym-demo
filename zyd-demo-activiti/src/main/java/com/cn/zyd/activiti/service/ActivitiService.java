package com.cn.zyd.activiti.service;

import com.cn.zyd.activiti.dao.ActReDeploymentDao;
import com.cn.zyd.activiti.entity.ActReDeployment;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ActivitiService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ActReDeploymentDao actReDeploymentDao;

    /**
     * 根据bpmn转换成sql
     */
    public void bpmnXml2Sql(String deploymentName, String bpmnXml) {
        //1 部署实例
        Deployment deployment = repositoryService.createDeployment().addString(deploymentName, bpmnXml).deploy();
        //2 从数据库获取添加的实例转成sql
        String deploymentId = deployment.getId();
        System.out.println(deploymentId);
        Map<String,Object> map = actReDeploymentDao.selectById(deploymentId);
        System.out.println("aaaa:" + map);
    }
}
