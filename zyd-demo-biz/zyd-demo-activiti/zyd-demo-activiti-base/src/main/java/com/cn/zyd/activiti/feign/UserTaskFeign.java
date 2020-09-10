package com.cn.zyd.activiti.feign;

import com.cn.zyd.activiti.dto.*;
import com.cn.zyd.activiti.query.ProcessQuery;
import com.cn.zyd.base.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zyd
 * @date 2019/10/9 17:41
 * @desc
 */
@FeignClient(name = "zyd-demo-activiti", path = "/task")
public interface UserTaskFeign {

    @GetMapping("queryTaskByUserId")
    Result<List<TaskDto>> queryTaskByUserId(
            @RequestParam("userId") String userId,
            @RequestParam("start") int start,
            @RequestParam("end") int end
    );

    @PostMapping("completeTask")
    Result<Void> completeTask(@RequestBody CompleteTaskDto completeTaskDto);

    @GetMapping("queryHisComment")
    Result<List<String>> queryHisComment(
            @RequestParam("userId") String userId,
            @RequestParam("taskId") String taskId);

    @GetMapping("queryHistoryTask")
    Result<List<HisTaskDto>> queryHistoryTask(
            @RequestParam("userId") String userId,
            @RequestParam("start") int start,
            @RequestParam("end") int end);

    @PostMapping("changeAssigner")
    Result<Void> changeAssigner(
            @RequestParam("toUserId") String toUserId,
            @RequestParam("comment") String comment,
            @RequestParam("taskId") String taskId
    );


    @PostMapping("continueProcess")
    Result<Void> continueProcess(
            @RequestParam("processInstanceId") String processInstanceId,
            @RequestParam("comment") String comment
    );

    @PostMapping("revoke")
    Result<Void> revoke(
            @RequestParam("hisTaskId") String hisTaskId,
            @RequestParam("userId") String userId
    );

}
