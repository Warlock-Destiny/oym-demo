package com.cn.zyd.activiti.controller;

import com.cn.zyd.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyd
 * @date 2019/9/26 23:43
 * @desc
 */
@RestController
@RequestMapping("/bpmn")
public class BpmnController extends BaseController {

    @PostMapping("/bpmnSql")
    public String bpmnSql(String bpmnXml) {
        return "";
    }
}
