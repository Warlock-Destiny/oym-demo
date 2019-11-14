package com.ctff.cloud.activiti.service.impl;

import com.cn.zyd.activiti.dto.CompleteTaskDto;
import com.cn.zyd.activiti.dto.TaskDto;
import com.cn.zyd.activiti.dto.TaskParam;
import com.cn.zyd.activiti.service.UserTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zyd
 * @date 2019/10/8 16:49
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTaskServiceImplTest {
    @Autowired
    private UserTaskService userTaskService;

    @Test
    public void queryMyTask() {
        List<TaskDto> taskDtoList = userTaskService.queryTaskByUser("王五", 0, 10);
        System.out.println(taskDtoList);
    }

    @Test
    public void testComplete() {
        TaskParam taskParam = new TaskParam();
        List<String> list = new ArrayList<>();
        list.add("王五");
        list.add("赵六");
        list.add("哈奇");
    }


    @Test
    public void testComplete2() {
        CompleteTaskDto completeTaskDto = new CompleteTaskDto()
                .setTaskId("6c05258a-f94f-11e9-989a-3c918097a91a")
                .setUserId("张三")
                .setComment("审批通过");
        userTaskService.completeTask(completeTaskDto);
    }

    @Test
    public void queryHisTask() {
        System.out.println(userTaskService.queryHistoryTask("张三", 0, 10));
    }

    @Test
    public void queryHisComment() {
        System.out.println(userTaskService.queryHisComment("张三", "e230b5ef-ea5a-11e9-bac1-3c918097a91a"));
    }
}
