package com.cn.zyd.activiti.service.impl;

import com.cn.zyd.activiti.dto.HisTaskDto;
import com.cn.zyd.activiti.dto.TaskDto;
import com.cn.zyd.activiti.service.UserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zyd
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

    /**
     * 根据用户id查询自己的任务
     */
    @Override
    public List<TaskDto> queryTaskByUserId(String userId, int start, int end) {
        List<TaskDto> taskDtoList = new ArrayList<>();
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(userId).listPage(start, end);
        taskList.forEach(task -> taskDtoList.add(new TaskDto()
                .setId(task.getId())
                .setName(task.getName())
                .setAssignee(task.getAssignee())
                .setOwner(task.getOwner())
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
    public void completeTask(String userId, String taskId, String comment, Map<String, Object> params) {
        Task task = taskService.createTaskQuery().taskAssignee(userId).taskId(taskId).singleResult();
        if (task == null) {
            log.warn("该任务不属于:userId:{},taskId:{}", userId, taskId);
            return;
        }
        if (StringUtils.isNoneBlank(comment)) {
            String processInstantId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
            taskService.addComment(taskId, processInstantId, comment);
        }
        taskService.setVariables(taskId, params);
        taskService.complete(taskId);
    }


    @Override
    public List<String> queryHisComment(String userId, String taskId) {
        String processInstantId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
        List<Comment> commentList = taskService.getProcessInstanceComments(processInstantId);
        List<String> commentMsgList = new ArrayList<>();
        commentList.forEach(comment -> commentMsgList.add(comment.getUserId()));
        return commentMsgList;
    }

    /**
     * 查看历史任务
     */
    @Override
    public List<HisTaskDto> queryHistoryTask(String userId, int start, int end) {
        List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(userId)
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
}
