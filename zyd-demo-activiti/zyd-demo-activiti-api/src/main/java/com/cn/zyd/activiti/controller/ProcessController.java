package com.cn.zyd.activiti.controller;

import com.cn.zyd.activiti.dto.CommentDto;
import com.cn.zyd.activiti.dto.HistoricProcessInstanceDto;
import com.cn.zyd.activiti.dto.ProcessStartDto;
import com.cn.zyd.activiti.query.ProcessQuery;
import com.cn.zyd.activiti.service.ProcessService;
import com.cn.zyd.base.controller.BaseController;
import com.cn.zyd.base.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zyd
 * @date 2019/10/9 17:12
 */
@RestController
@RequestMapping("process")
@Api(value = "流程操作", tags = "外部接口调用")
public class ProcessController extends BaseController {

    @Autowired
    private ProcessService processService;

    @ApiOperation("根据调用的提供的processDefinitionId启动流程")
    @PostMapping("start")
    public Result<String> startProcess(
            @RequestBody ProcessStartDto processStartDto
    ) {
        String processInstanceId = processService.startProcess(processStartDto);
        return Result.<String>ok("执行成功").data(processInstanceId);
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
        return Result.<List<HistoricProcessInstanceDto>>ok("执行成功").data(processService.getHisProcessInstantByUserId(processQuery));
    }

    @ApiOperation("查看详情")
    @GetMapping("detail")
    public Result<List<CommentDto>> detail(
            @RequestBody ProcessQuery processQuery
    ) {
        return Result.<List<CommentDto>>ok().data(processService.detail(processQuery));
    }

    @ApiOperation("查看任务是否完成")
    @GetMapping("isComplete")
    Result<Boolean> isComplete(
            @RequestParam("processInstantId") String processInstantId
    ) {
        return Result.<Boolean>ok().data(processService.isComplete(processInstantId));
    }

}
