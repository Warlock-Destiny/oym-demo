package com.cn.zyd.activiti.service.impl;

import com.cn.zyd.activiti.dto.CommentDto;
import com.cn.zyd.activiti.dto.HistoricProcessInstanceDto;
import com.cn.zyd.activiti.dto.ProcessStartDto;
import com.cn.zyd.activiti.query.ProcessQuery;
import com.cn.zyd.activiti.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.TaskInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 用户启动流程
     * 注：userId会从auth中获取
     *
     * @return 返回processInstanceId 用于与业务表相关联
     * 此字段用于:
     * 1 用于查看自己发起的正在执行中的流程
     * 2 用于查看历史流程记录
     */
    @Override
    public String startProcess(ProcessStartDto processStartDto) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processStartDto.getProcessDefinitionKey(), processStartDto.getBusinessKey(), processStartDto.getTaskParam());
        String processName = processStartDto.getProcessName();
        if (StringUtils.isBlank(processName)) {
            processName = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processStartDto.getProcessDefinitionKey()).singleResult().getName();
            runtimeService.setProcessInstanceName(processInstance.getId(), processName);
        }
        return processInstance.getId();
    }

    @Override
    public void closeProcess(String processInstanceId, String reason) {
        runtimeService.deleteProcessInstance(processInstanceId, reason);
    }

    /**
     * 目前只提供根据用户名查看历史(未来可能会根据业务类型进行区分)
     */
    @Override
    public List<HistoricProcessInstanceDto> getHisProcessInstantByUserId(ProcessQuery processQuery) {
        int start = processQuery.getCurrent() * processQuery.getSize();
        int end = start + processQuery.getSize();
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery()
                .startedBy(processQuery.getUserId());
        if (processQuery.getFinish() != null) {
            if (processQuery.getFinish()) {
                historicProcessInstanceQuery.finished();
            } else {
                historicProcessInstanceQuery.unfinished();
            }
        }
        List<HistoricProcessInstance> historicProcessInstanceList = historicProcessInstanceQuery.listPage(start, end);
        return historicProcessInstanceList.stream().map(x -> new HistoricProcessInstanceDto()
                .setDeploymentId(x.getId())
                .setName(x.getName())
                .setStartUserId(x.getStartUserId())
                .setDeploymentId(x.getDeploymentId())
                .setProcessDefinitionId(x.getProcessDefinitionId())
                .setStartTime(x.getStartTime())
                .setEndTime(x.getEndTime())).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> detail(ProcessQuery processQuery) {
        List<Comment> commentList = taskService.getProcessInstanceComments(processQuery.getProcessInstanceId());
        return commentList.stream().map(x -> new CommentDto()
                .setId(x.getId())
                .setUserId(x.getUserId())
                .setTaskId(x.getTaskId())
                .setProcessInstanceId(x.getProcessInstanceId())
                .setTime(x.getTime())
                .setFullMessage(x.getFullMessage())).collect(Collectors.toList());
    }

    /**
     * 查看当前流程执行的任务id
     * 场景
     * 想查看进行中的流程的任务在哪个步骤
     */
    @Override
    public String getTaskIdByProcessInstantId(ProcessQuery processQuery) {
        TaskInfo taskInfo = taskService.createTaskQuery().processInstanceId(processQuery.getProcessInstanceId()).singleResult();
        return taskInfo.getId();
    }

    @Override
    public Boolean isComplete(String processInstantId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstantId).singleResult();
        return processInstance == null;
    }

}
