package com.cn.zyd.activiti.controller;

import com.cn.zyd.activiti.dto.CompleteTaskDto;
import com.cn.zyd.activiti.dto.HisTaskDto;
import com.cn.zyd.activiti.dto.TaskDto;
import com.cn.zyd.activiti.service.UserTaskService;
import com.cn.zyd.base.controller.BaseController;
import com.cn.zyd.base.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zyd
 * @date 2019/10/9 17:41
 * @desc
 */
@RestController
@RequestMapping("task")
public class UserTaskController extends BaseController {

    @Autowired
    private UserTaskService userTaskService;

    @GetMapping("queryTaskByUserId")
    public Result<List<TaskDto>> queryTaskByUserId(
            @RequestParam("userId") String userId,
            @RequestParam("start") int start,
            @RequestParam("end") int end) {
        return Result.<List<TaskDto>>ok().data(userTaskService.queryTaskByUser(userId, start, end));
    }

    @PostMapping("completeTask")
    public Result completeTask(
            @RequestBody CompleteTaskDto completeTaskDto) {
        userTaskService.completeTask(completeTaskDto);
        return Result.ok();
    }

    @GetMapping("queryHisComment")
    public Result<List<String>> queryHisComment(
            @RequestParam("userId") String userId,
            @RequestParam("taskId") String taskId) {
        return Result.<List<String>>ok().data(userTaskService.queryHisComment(userId, taskId));
    }

    @GetMapping("queryHistoryTask")
    public Result<List<HisTaskDto>> queryHistoryTask(
            @RequestParam("userId") String userId,
            @RequestParam("start") int start,
            @RequestParam("end") int end
    ) {
        return Result.<List<HisTaskDto>>ok().data(userTaskService.queryHistoryTask(userId, start, end));
    }

    @PostMapping("changeAssigner")
    public Result changeAssigner(
            @RequestParam("toUserId") String toUserId,
            @RequestParam("comment") String comment,
            @RequestParam("taskId") String taskId
    ) {
        userTaskService.setAssignUser(toUserId, comment, taskId);
        return Result.ok();
    }

}