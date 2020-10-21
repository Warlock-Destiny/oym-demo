package com.oym.activiti.service;


import com.oym.activiti.dto.CompleteTaskDto;
import com.oym.activiti.dto.HisTaskDto;
import com.oym.activiti.dto.TaskDto;
import com.oym.base.model.Result;

import java.util.List;

/**
 * @author zhangyd
 * @date 2019/10/8 11:09
 * @desc
 */
public interface UserTaskService {

    /**
     * 提供给外部接口使用，查看自身的任务列表
     *
     * @param userId
     * @return
     */
    List<TaskDto> queryTaskByUser(String userId, int start, int end);

    /**
     * 完成任务
     */
    void completeTask(CompleteTaskDto completeTaskDto);


    /**
     * 展示任务历史评论
     */
    List<String> queryHisComment(String userId, String taskId);

    /**
     * 查看历史任务
     */
    List<HisTaskDto> queryHistoryTask(String userId, int start, int end);

    /**
     * 转交任务
     */
    void setAssignUser(String toUserId, String comment, String taskId);

    /**
     * 结束所有的任务
     */
    void completeAllTaskByProcessId(String processInstantId, String comment);

    /**
     * 撤销功能 撤销功能只能将流程的下一步转移到上一步 如果业务出现任务移交的情况需要业务自己做处理
     */
    Result<Void> revoke(String hisTaskId, String userId);


}
