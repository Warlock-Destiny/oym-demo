package com.cn.zyd.activiti.service;

import com.cn.zyd.activiti.dto.DeploymentDto;
import com.cn.zyd.activiti.dto.ProcessDto;

import java.util.List;

/**
 * @author zyd
 * @date 2019/10/8 10:40
 * @desc 部署提供的服务
 */
public interface DeploymentService {
    /**
     * 部署流程 并且返回sql
     *
     * @param deploymentId 如果deploymenbtId为null，则代表插入，否则代表更新
     * @return 本次部署的sql
     */
    String deploy(String deployName, String deploymentId, String bpmnXml);

    /**
     * 展示已部署的列表
     */
    List<DeploymentDto> listDeployment();


    /**
     * 展示已有的流程
     */
    List<ProcessDto> listProcess();

    /**
     * 展示部署的具体xml
     *
     * @param deploymentId 部署id
     * @return 详细信息 带bpmn
     */
    DeploymentDto detail(String deploymentId);

    /**
     * 删除流程
     */
    String remove(String deploymentId);
}
