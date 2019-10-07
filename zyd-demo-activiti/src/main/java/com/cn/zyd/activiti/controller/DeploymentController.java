package com.cn.zyd.activiti.controller;

import com.cn.zyd.activiti.service.ActivitiService;
import com.cn.zyd.base.BaseController;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyd
 * @date 2019/9/26 23:43
 * @desc
 */
@RestController
@RequestMapping("/deployment")
public class DeploymentController extends BaseController {

    @Autowired
    private ActivitiService activitiService;

    /**
     * 部署
     */
    @PostMapping("deploy")
    @ResponseBody
    public String deplpyment(
            @Param("deployName") String deployName,
            @Param("bpmnXml") String bpmnXml) {
        return activitiService.deploy(deployName, bpmnXml);
    }
}
