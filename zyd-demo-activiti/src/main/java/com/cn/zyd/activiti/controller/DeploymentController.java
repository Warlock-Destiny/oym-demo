package com.cn.zyd.activiti.controller;

import com.cn.zyd.activiti.service.impl.DeploymentServiceImpl;
import com.cn.zyd.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyd
 * @date 2019/9/26 23:43
 * @desc 部署的一些操作
 */
@RestController
@RequestMapping("deployment")
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
    @PostMapping("deploy")
    public Result bpmnSql(
            @RequestParam("deployName") String deployName,
            @RequestParam(value = "deploymentId", required = false) String deploymentId,
            @RequestParam("bpmnXml") String bpmnXml) {
        return Result.ok().data(deploymentServiceImpl.deploy(deployName, deploymentId, bpmnXml));
    }


    /**
     * 展现已部署的列表
     */
    @GetMapping("listDeployment")
    public Result listDeployment() {
        return Result.ok().data(deploymentServiceImpl.listDeployment());
    }

    /**
     * 查看部署的流程实例
     */
    @GetMapping("listProcess")
    public Result listProcess() {
        return Result.ok().data(deploymentServiceImpl.listProcess());
    }

    /**
     * 查看部署的详情
     */
    @GetMapping("detail/{deploymentId}")
    public Result detail(
            @PathVariable("deploymentId") String deploymentId) {
        return Result.ok().data(deploymentServiceImpl.detail(deploymentId));
    }

    /**
     * 删除流程
     */
    @GetMapping("remove/{deploymentId}")
    public Result remove(
            @PathVariable("deploymentId") String deploymentId
    ) {
        return Result.ok().data(deploymentServiceImpl.remove(deploymentId));
    }
}
