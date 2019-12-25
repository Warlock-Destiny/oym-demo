package com.cn.zyd.activiti.controller;

import com.cn.zyd.activiti.dto.DeploymentDto;
import com.cn.zyd.activiti.dto.ProcessDto;
import com.cn.zyd.activiti.service.impl.DeploymentServiceImpl;
import com.cn.zyd.base.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangyd
 * @date 2019/9/26 23:43
 */
@RestController
@RequestMapping("deployment")
@Api(value = "部署操作", tags = "仅供开发人员使用")
public class DeploymentController {

    @Autowired
    private DeploymentServiceImpl deploymentServiceImpl;

    /**
     * 提供部署使用
     *
     * @param deployName   部署名称
     * @param deploymentId 部署id 如果部署id存在，则代表更新
     * @param bpmnXml      部署的内容
     * @return 本次操作的的sql
     */
    @ApiOperation("流程部署及返回本次部署的sql")
    @PostMapping("deploy")
    public Result<String> bpmnSql(
            @RequestParam("deployName") String deployName,
            @RequestParam(value = "deploymentId", required = false) String deploymentId,
            @RequestParam("bpmnXml") String bpmnXml
    ) {
        return Result.<String>ok().data(deploymentServiceImpl.deploy(deployName, deploymentId, bpmnXml));
    }


    /**
     * 展现已部署的列表
     */
    @ApiOperation("查看已有的部署的列表")
    @GetMapping("listDeployment")
    public Result<List<DeploymentDto>> listDeployment() {
        return Result.<List<DeploymentDto>>ok().data(deploymentServiceImpl.listDeployment());
    }

    /**
     * 查看部署的流程实例
     */
    @ApiOperation("查看已有的流程实例")
    @GetMapping("listProcess")
    public Result<List<ProcessDto>> listProcess() {
        return Result.<List<ProcessDto>>ok().data(deploymentServiceImpl.listProcess());
    }

    /**
     * 查看部署的详情
     */
    @GetMapping("detail/{deploymentId}")
    public Result<DeploymentDto> detail(
            @PathVariable("deploymentId") String deploymentId) {
        return Result.<DeploymentDto>ok().data(deploymentServiceImpl.detail(deploymentId));
    }

    /**
     * 删除流程
     */
    @PostMapping("remove/{deploymentId}")
    public Result<String> remove(
            @PathVariable("deploymentId") String deploymentId
    ) {
        return Result.<String>ok().data(deploymentServiceImpl.remove(deploymentId));
    }
}
