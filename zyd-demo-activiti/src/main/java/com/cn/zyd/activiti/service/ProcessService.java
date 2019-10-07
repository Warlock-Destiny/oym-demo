package com.cn.zyd.activiti.service;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zyd
 * @date 2019/10/2 15:42
 * @desc
 */
@Service
public class ProcessService {

    @Autowired
    private RuntimeService runtimeService;

    public void startProcess(String processKey) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId1", "张三");
        runtimeService.startProcessInstanceByKey(processKey, params);
    }
}
