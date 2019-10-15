package com.cn.zyd.activiti.service;

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
        System.out.println(userTaskService.queryTaskByUserId("张三"));;
    }

    @Test
    public void completeTask() {
        userTaskService.completeTask("李四","5deed95c-e915-11e9-aabf-3c918097a91a");
    }

    @Test
    public void getHistoryTask(){
        System.out.println(userTaskService.queryHistoryTask("张三"));
    }
}
