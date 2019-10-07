package com.cn.zyd.activiti.controller;

import com.cn.zyd.activiti.service.ProcessService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyd
 * @date 2019/10/2 15:42
 * @desc
 */
@RestController
@RequestMapping("/process")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    /**
     * 执行流程
     */
    @PostMapping("startProcess")
    @ResponseBody
    public String queryProcess(
            @Param("userId") String userId,
            @Param("processId") String processKey) {
        processService.startProcess("测试serviceTask流程");
        return null;
    }
}
