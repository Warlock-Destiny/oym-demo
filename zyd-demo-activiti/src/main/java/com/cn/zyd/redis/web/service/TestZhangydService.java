package com.cn.zyd.redis.web.service;

import com.cn.zyd.redis.web.dao.TestZhangydDao;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestZhangydService {
    @Autowired
    private TestZhangydDao testZhangydDao;

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;

    public void deploy() {
        repositoryService.createDeployment().addClasspathResource("bpmn/test.bpmn").deploy();
    }

    public void test() {
        runtimeService.startProcessInstanceByKey("aaaaaaaa");
    }
}