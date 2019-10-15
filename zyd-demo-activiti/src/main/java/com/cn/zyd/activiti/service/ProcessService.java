package com.cn.zyd.activiti.service;

import com.cn.zyd.activiti.dto.CommentDto;
import com.cn.zyd.activiti.dto.HistoricProcessInstanceDto;

import java.util.List;
import java.util.Map;

/**
 * @author zyd
 * @date 2019/10/2 15:42
 * @desc
 */
public interface ProcessService {

    /**
     * 启动流程
     *
     * @param processDefinitionId 流程的key
     * @param params              流程启动参数
     * @return processInstanceId
     */
    String startProcess(String processDefinitionId, String processName, Map<String, Object> params);

    /**
     * 用户查看自己发起的流程实例
     */
    List<HistoricProcessInstanceDto> getHisProcessInstantByUserId(String userId);

    /**
     * 根据processInstanceId查看流程的状态
     */
    List<CommentDto> detail(String processInstanceId);

    /**
     * 关闭流程
     *
     * @param processInstanceId 流程实例id
     */
    void closeProcess(String processInstanceId, String reason);

}
