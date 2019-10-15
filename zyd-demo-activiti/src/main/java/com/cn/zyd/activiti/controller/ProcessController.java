package com.cn.zyd.activiti.controller;

import com.cn.zyd.activiti.dto.ProcessStartDto;
import com.cn.zyd.activiti.service.ProcessService;
import com.cn.zyd.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zyd
 * @date 2019/10/9 17:12
 * @desc
 */
@RestController
@RequestMapping("process")
public class ProcessController {
    @Autowired
    private ProcessService processService;


    @PostMapping("start")
    public Result startProcess(
            @RequestBody ProcessStartDto processStartDto
    ) {
        String processInstanceId = processService.startProcess(processStartDto.getProcessDefinitionId(), processStartDto.getProcessName(), processStartDto.getParams());
        return Result.ok("执行成功").data(processInstanceId);
    }

    @GetMapping("hisProcessInstant")
    public Result startProcess(
            @RequestParam("userId") String userId
    ) {
        return Result.ok("执行成功").data(processService.getHisProcessInstantByUserId(userId));
    }

    @GetMapping("close")
    public Result closeProcess(
            @RequestParam("processInstanceId") String processInstanceId,
            @RequestParam("reason") String reason
    ) {
        processService.closeProcess(processInstanceId, reason);
        return Result.ok("关闭流程成功");
    }
}
