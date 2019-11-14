package com.cn.zyd.activiti.feign;

import com.cn.zyd.activiti.dto.CompleteTaskDto;
import com.cn.zyd.activiti.dto.HisTaskDto;
import com.cn.zyd.activiti.dto.TaskDto;
import com.cn.zyd.base.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zyd
 * @date 2019/10/9 17:41
 * @desc
 */
@FeignClient(name = "ctff-cloud-activiti", path = "/task")
public interface UserTaskFeign {

    @GetMapping("queryTaskByUserId")
    Result<List<TaskDto>> queryTaskByUserId(
            @RequestParam("userId") String userId,
            @RequestParam("start") int start,
            @RequestParam("end") int end
    );

    @PostMapping("completeTask")
    Result completeTask(@RequestBody CompleteTaskDto completeTaskDto);

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
    Result changeAssigner(
            @RequestParam("toUserId") String toUserId,
            @RequestParam("comment") String comment,
            @RequestParam("taskId") String taskId
    );

}
