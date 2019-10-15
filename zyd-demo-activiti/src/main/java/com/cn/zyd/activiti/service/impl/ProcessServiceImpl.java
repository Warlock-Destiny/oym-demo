package com.cn.zyd.activiti.service.impl;

import com.cn.zyd.activiti.dto.CommentDto;
import com.cn.zyd.activiti.dto.HistoricProcessInstanceDto;
import com.cn.zyd.activiti.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zyd
 * @date 2019/10/8 10:43
 * @desc 流程服务
 */
@Service
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Override
    public String startProcess(String processDefinitionId, String processName, Map<String, Object> params) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, params);
        if (StringUtils.isBlank(processName)) {
            processName = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult().getName();
            runtimeService.setProcessInstanceName(processInstance.getId(), processName);
        }
        return processInstance.getId();
    }

    @Override
    public List<HistoricProcessInstanceDto> getHisProcessInstantByUserId(String userId) {
        List<HistoricProcessInstance> historicProcessInstanceList = historyService.createHistoricProcessInstanceQuery().startedBy(userId).list();
        List<HistoricProcessInstanceDto> historicProcessInstanceDtoList = new ArrayList<>();
        historicProcessInstanceList.forEach(x ->
                historicProcessInstanceDtoList.add(new HistoricProcessInstanceDto()
                        .setDeploymentId(x.getId())
                        .setName(x.getName())
                        .setStartUserId(x.getStartUserId())
                        .setDeploymentId(x.getDeploymentId())
                        .setProcessDefinitionId(x.getProcessDefinitionId())
                        .setStartTime(x.getStartTime())
                        .setEndTime(x.getEndTime())
                ));

        return historicProcessInstanceDtoList;
    }

    @Override
    public List<CommentDto> detail(String processInstanceId) {
        List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);
        List<CommentDto> commentMsgList = new ArrayList<>();
        commentList.forEach(x -> commentMsgList.add(new CommentDto()
                .setId(x.getId())
                .setUserId(x.getUserId())
                .setTaskId(x.getTaskId())
                .setProcessInstanceId(x.getProcessInstanceId())
                .setTime(x.getTime())
                .setFullMessage(x.getFullMessage())));
        return commentMsgList;
    }

    @Override
    public void closeProcess(String processInstanceId, String reason) {
        runtimeService.deleteProcessInstance(processInstanceId, reason);
    }


}
