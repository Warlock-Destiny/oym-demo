package com.cn.zyd.activiti.service;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyd
 * @date 2019/10/6 16:05
 * @desc
 */
@Service
public class UserTaskService {
    @Autowired
    private TaskService taskService;

    public void queryTaskByUserId(String userId) {
        Task task = taskService.createTaskQuery().taskAssignee(userId).singleResult();
        System.out.println(task.getName());

    }
}
