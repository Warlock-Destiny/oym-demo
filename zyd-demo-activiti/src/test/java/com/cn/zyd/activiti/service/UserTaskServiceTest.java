package com.cn.zyd.activiti.service;

import org.activiti.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zyd
 * @date 2019/10/6 16:10
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTaskServiceTest {

    @Autowired
    private UserTaskService userTaskService;

    @Test
    public void test() {
        userTaskService.queryTaskByUserId("张三");
    }
}
