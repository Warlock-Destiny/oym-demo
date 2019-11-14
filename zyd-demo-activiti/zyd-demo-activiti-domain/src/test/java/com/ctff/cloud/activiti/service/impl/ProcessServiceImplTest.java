package com.ctff.cloud.activiti.service.impl;

import com.cn.zyd.activiti.dto.ProcessStartDto;
import com.cn.zyd.activiti.dto.TaskParam;
import com.cn.zyd.activiti.service.ProcessService;
import com.cn.zyd.activiti.service.UserTaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zyd
 * @date 2019/10/8 16:37
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessServiceImplTest {
    @Autowired
    public ProcessService processService;
    @Autowired
    private UserTaskService userTaskService;

    @Test
    public void testStartProcess() {
        ProcessStartDto processStartDto = new ProcessStartDto();
        processStartDto.setProcessDefinitionKey("process")
                .setProcessName("测试流程1")
                .setTaskParam(new TaskParam());
        processService.startProcess(processStartDto);
    }

    @Test
    public void testStartProcessWithParam() {
        Authentication.setAuthenticatedUserId("张三");
        Map<String, Object> param = new HashMap<>();
        param.put("start_user", "张三");
        ProcessStartDto processStartDto = new ProcessStartDto();
        processStartDto.setProcessDefinitionKey("process")
                .setProcessName("测试流程1")
                .setTaskParam(new TaskParam());
        String processInstantId = processService.startProcess(processStartDto);
    }

    @Test
    public void testClose() {
        processService.closeProcess("20dac847-e9a7-11e9-9d5e-3c918097a91a", "肚子饿了");
    }
}
