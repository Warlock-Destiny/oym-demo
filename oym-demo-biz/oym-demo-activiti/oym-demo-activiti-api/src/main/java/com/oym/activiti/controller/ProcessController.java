package com.oym.activiti.controller;

import com.oym.activiti.dto.CommentDto;
import com.oym.activiti.dto.HistoricProcessInstanceDto;
import com.oym.activiti.dto.ProcessStartDto;
import com.oym.activiti.query.ProcessQuery;
import com.oym.activiti.service.ProcessService;
import com.oym.base.web.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangyd
 * @date 2019/10/9 17:12
 */
@RestController
@RequestMapping("process")
@Api(value = "流程操作", tags = "外部接口调用")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @ApiOperation("根据调用的提供的processDefinitionId启动流程")
    @PostMapping("start")
    public Result<String> startProcess(
            @RequestBody ProcessStartDto processStartDto
    ) {
        String processInstanceId = processService.startProcess(processStartDto);
        return Result.okData(processInstanceId);
    }

    @ApiOperation("关闭正在执行中的流程")
    @PostMapping("close")
    public Result closeProcess(
            @RequestParam("processInstanceId") String processInstanceId,
            @RequestParam(value = "reason", required = false) String reason
    ) {
        processService.closeProcess(processInstanceId, reason);
        return Result.ok("关闭流程成功");
    }

    @ApiOperation("查看历史的流程实例")
    @GetMapping("hisProcessInstant")
    public Result<List<HistoricProcessInstanceDto>> getHisProcessInstantByUserId(
            @RequestBody ProcessQuery processQuery
    ) {
        return Result.okData(processService.getHisProcessInstantByUserId(processQuery));
    }

    @ApiOperation("查看详情")
    @GetMapping("detail")
    public Result<List<CommentDto>> detail(
            @RequestBody ProcessQuery processQuery
    ) {
        return Result.okData(processService.detail(processQuery));
    }

    @ApiOperation("查看任务是否完成")
    @GetMapping("isComplete")
    Result<Boolean> isComplete(
            @RequestParam("processInstantId") String processInstantId
    ) {
        return Result.okData(processService.isComplete(processInstantId));
    }

}
