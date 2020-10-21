package com.oym.activiti.service.impl;

import com.oym.activiti.custom.CustomerService;
import com.oym.activiti.dto.CompleteTaskDto;
import com.oym.activiti.dto.HisTaskDto;
import com.oym.activiti.dto.TaskDto;
import com.oym.activiti.dto.TaskParam;
import com.oym.activiti.service.ProcessService;
import com.oym.activiti.service.UserTaskService;
import com.oym.base.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangyd
 * @date 2019/10/6 16:05
 * @desc 任务的一些服务
 */
@Service
@Slf4j
public class UserTaskServiceImpl implements UserTaskService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProcessService processService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private CustomerService customerService;

    /**
     * 根据用户id查询自己的任务
     */
    @Override
    public List<TaskDto> queryTaskByUser(String username, int start, int end) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<Task> taskList = taskService.createTaskQuery().includeTaskLocalVariables()
                .taskCandidateOrAssigned(username, Collections.emptyList())
                .listPage(start, end);
        taskList.forEach(task -> taskDtoList.add(new TaskDto()
                .setId(task.getId())
                .setName(task.getName())
                .setAssignee(task.getAssignee())
                .setOwner(task.getOwner())
                .setProcessInstanceId(task.getProcessInstanceId())
                .setTaskLocalVariables(task.getTaskLocalVariables())
                .setProcessVariables(task.getProcessVariables())
                .setDescription(task.getDescription())
                .setClaimTime(task.getClaimTime())
                .setCreateTime(task.getCreateTime())
        ));
        return taskDtoList;
    }

    /**
     * 结束任务
     * 并且添加评论
     */
    @Override
    public void completeTask(CompleteTaskDto completeTaskDto) {
        String taskId = completeTaskDto.getTaskId();
        String comment = completeTaskDto.getComment();
        TaskParam taskParam = completeTaskDto.getTaskParam();
        Task task = taskService.createTaskQuery().taskCandidateOrAssigned(completeTaskDto.getUserId(), Collections.emptyList()).taskId(completeTaskDto.getTaskId()).singleResult();
        if (task == null) {
            log.warn("该任务不属于:userId:{},taskId:{}", completeTaskDto.getUserId(), taskId);
            return;
        }
        addComment(taskId, comment);
        taskService.setVariables(taskId, taskParam);
        taskService.complete(taskId, taskParam);
    }


    @Override
    public List<String> queryHisComment(String userId, String taskId) {
        HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        if (historicTaskInstance == null) {
            return Collections.emptyList();
        }
        String processInstantId = historicTaskInstance.getProcessInstanceId();
        List<Comment> commentList = taskService.getProcessInstanceComments(processInstantId);
        List<String> commentMsgList = new ArrayList<>();
        commentList.forEach(comment -> commentMsgList.add(comment.getFullMessage()));
        return commentMsgList;
    }

    /**
     * 查看历史任务
     */
    @Override
    public List<HisTaskDto> queryHistoryTask(String username, int start, int end) {
        List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(username)
                .finished()
                .listPage(start, end);
        List<HisTaskDto> hisTaskDtoList = new ArrayList<>();
        historicTaskInstanceList.forEach(historicTaskInstance -> {
            HisTaskDto hisTaskDto = new HisTaskDto();
            hisTaskDto.setId(historicTaskInstance.getId())
                    .setName(historicTaskInstance.getName())
                    .setAssignee(historicTaskInstance.getAssignee())
                    .setOwner(historicTaskInstance.getOwner())
                    .setDescription(historicTaskInstance.getDescription())
                    .setClaimTime(historicTaskInstance.getClaimTime())
                    .setCreateTime(historicTaskInstance.getCreateTime());
            hisTaskDto.setStartTime(historicTaskInstance.getStartTime())
                    .setEndTime(historicTaskInstance.getEndTime())
                    .setTime(historicTaskInstance.getTime())
                    .setDurationInMillis(historicTaskInstance.getDurationInMillis())
                    .setWorkTimeInMillis(historicTaskInstance.getWorkTimeInMillis())
                    .setDeleteReason(historicTaskInstance.getDeleteReason());
            hisTaskDtoList.add(hisTaskDto);
        });
        return hisTaskDtoList;
    }

    @Override
    public void setAssignUser(
            String toUserName,
            String comment,
            String taskId
    ) {
        addComment(taskId, comment);
        taskService.setAssignee(taskId, toUserName);
    }

    @Override
    public void completeAllTaskByProcessId(String processInstantId, String comment) {
        List<String> taskIdList = taskService.createTaskQuery().processInstanceId(processInstantId).list().stream().map(TaskInfo::getId).collect(Collectors.toList());
        taskIdList.forEach(taskId -> {
            taskService.addComment(taskId, processInstantId, comment);
            taskService.complete(taskId);
        });
    }

    /**
     * 回收 只能回收一步
     */
    @Override
    @Transactional
    public Result<Void> revoke(String hisTaskId, String userId) {
        // 获取历史节点信息
        HistoricTaskInstance taskInstance = historyService.createHistoricTaskInstanceQuery().taskId(hisTaskId).singleResult();
        if (taskInstance == null) {
            return Result.fail("历史任务id:" + hisTaskId + "找不到");
        }
        String processInstanceId = taskInstance.getProcessInstanceId();
        Boolean result = processService.isComplete(processInstanceId);
        if (result) {
            return Result.fail("流程已经结束,无法回收");
        }
        if (!StringUtils.equals(taskInstance.getAssignee(), userId)) {
            return Result.fail("该任务非当前用户提交,无法回收");
        }
        BpmnModel bpmnModel = repositoryService.getBpmnModel(taskInstance.getProcessDefinitionId());
        Process process = bpmnModel.getProcesses().get(0);
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        FlowNode targetNode = (FlowNode) process.getFlowElement(taskInstance.getTaskDefinitionKey());
        if (!getHisNodeNext(process, targetNode.getId(), taskList)) {
            return Result.fail("流程已被处理，无法回收");
        }
        int count = 1;
        // 制作新的任务流程
        for (Task task : taskList) {
            String taskId = task.getId();
            FlowNode currentNode = (FlowNode) process.getFlowElement(task.getTaskDefinitionKey());
            Map<String, Object> variables = new HashMap<>(1);
            count--;
            if (count == 0) {
                changeTurnAround(processInstanceId, currentNode, targetNode, () -> taskService.complete(taskId, variables));
                historyService.deleteHistoricTaskInstance(taskId);
            } else {
                task2Over(processInstanceId, currentNode, () -> taskService.complete(taskId, variables));
                historyService.deleteHistoricTaskInstance(taskId);
            }
        }
        customerService.deleteHistory(hisTaskId);
        String taskId = taskService.createTaskQuery().taskAssignee(userId).processInstanceId(processInstanceId).singleResult().getId();
        taskService.addComment(taskId, processInstanceId, "回收任务");
        return Result.ok();
    }

    /**
     * 获取下一个流程是否都未处理
     */
    private boolean getHisNodeNext(Process process, String targetTaskId, List<Task> taskList) {
        for (Task task : taskList) {
            String currentTaskKey = task.getTaskDefinitionKey();
            FlowNode currentNode = (FlowNode) process.getFlowElement(currentTaskKey);
            SequenceFlow sequenceFlow = currentNode.getIncomingFlows().get(0);
            FlowNode beforeNode = (FlowNode) process.getFlowElement(sequenceFlow.getSourceFlowElement().getId());
            if (!StringUtils.equals(targetTaskId, beforeNode.getId())) {
                return false;
            }
        }
        return true;

    }

    /**
     * 因为流程走向修改的是cache的内容 让任务完成的时候读的是走向的内容
     *
     * @param processId   流程id 用于防止同一个流程在进行的时候读到修改的流程内容
     * @param currentNode 当前节点
     * @param targetNode  目标节点
     * @param runnable    执行的函数
     */
    private void changeTurnAround(
            String processId,
            FlowNode currentNode,
            FlowNode targetNode,
            Runnable runnable
    ) {
        //保留员流程走向
        List<SequenceFlow> outComingSequenceFlows = currentNode.getOutgoingFlows();
        //新的流程走向
        List<SequenceFlow> newOutComingSequenceFlows = createNewFlow(currentNode, targetNode);
        synchronized (processId.intern()) {
            try {
                //设置新流程走向
                currentNode.setOutgoingFlows(newOutComingSequenceFlows);
                runnable.run();
            } finally {
                // 还原流程
                currentNode.setOutgoingFlows(outComingSequenceFlows);
            }
        }
    }

    private void task2Over(String processId,
                           FlowNode currentNode,
                           Runnable runnable) {
        //保留员流程走向
        List<SequenceFlow> outComingSequenceFlows = currentNode.getOutgoingFlows();
        //新的流程走向
        List<SequenceFlow> newOutComingSequenceFlows = Collections.emptyList();
        synchronized (processId.intern()) {
            try {
                //设置新流程走向
                currentNode.setOutgoingFlows(newOutComingSequenceFlows);
                runnable.run();
            } finally {
                // 还原流程
                currentNode.setOutgoingFlows(outComingSequenceFlows);
            }
        }
    }

    private List<SequenceFlow> createNewFlow(FlowNode currentNode, FlowNode targetNode) {
        SequenceFlow sequenceFlow = new SequenceFlow();
        sequenceFlow.setSourceFlowElement(currentNode);
        sequenceFlow.setTargetFlowElement(targetNode);
        sequenceFlow.setId("callback-flow");
        List<SequenceFlow> newOutComingSequenceFlows = new ArrayList<>();
        newOutComingSequenceFlows.add(sequenceFlow);
        return newOutComingSequenceFlows;
    }

    private void addComment(String taskId, String comment) {
        if (StringUtils.isNoneBlank(comment)) {
            String processInstantId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
            taskService.addComment(taskId, processInstantId, comment);
        }
    }

}
