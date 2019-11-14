package com.cn.zyd.activiti.service;


import com.cn.zyd.activiti.dto.CompleteTaskDto;
import com.cn.zyd.activiti.dto.HisTaskDto;
import com.cn.zyd.activiti.dto.TaskDto;

import java.util.List;

/**
 * @author zyd
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
     *
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


}
