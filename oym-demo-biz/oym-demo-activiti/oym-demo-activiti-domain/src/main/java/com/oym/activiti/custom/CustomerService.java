package com.oym.activiti.custom;

import com.oym.activiti.custom.cmd.HistoryTaskCmd;
import org.activiti.engine.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangyd
 * @date 2019/12/25 16:41
 * @desc
 */
@Service
public class CustomerService {
    @Autowired
    private ManagementService managementService;

    public void deleteHistory(String taskId) {
        managementService.executeCommand(new HistoryTaskCmd(taskId));
    }

}
