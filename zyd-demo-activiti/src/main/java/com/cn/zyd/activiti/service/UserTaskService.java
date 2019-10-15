package com.cn.zyd.activiti.service;


import com.cn.zyd.activiti.dto.HisTaskDto;
import com.cn.zyd.activiti.dto.TaskDto;

import java.util.List;
import java.util.Map;

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
    List<TaskDto> queryTaskByUserId(String userId, int start, int end);

    /**
     * 完成任务
     *
     * @param userId 用户id
     * @param taskId 任务id
     */
    void completeTask(String userId, String taskId, String comment, Map<String, Object> params);


    /**
     * 展示任务历史评论
     */
    List<String> queryHisComment(String userId, String taskId);

    /**
     * 查看历史任务
     */
    List<HisTaskDto> queryHistoryTask(String userId, int start, int end);

}
