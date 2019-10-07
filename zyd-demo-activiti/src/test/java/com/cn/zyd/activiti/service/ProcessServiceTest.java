package com.cn.zyd.activiti.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zyd
 * @date 2019/10/4 22:46
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessServiceTest {

    @Autowired
    private ProcessService processService;

    @Test
    public void test(){
        processService.startProcess("helloworld");
    }

    @Test
    public void test2() {
        processService.startProcess("测试serviceTask流程");
    }


}
