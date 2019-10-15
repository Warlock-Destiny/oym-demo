package com.cn.zyd.activiti.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.activiti.engine.task.TaskInfo;

import java.util.Date;
import java.util.Map;

/**
 * @author zyd
 * @date 2019/10/8 11:09
 * @desc
 */
@Data
@Accessors(chain = true)
public class TaskDto implements TaskInfo {

    private String id;
    private String name;
    /**
     * 委托人
     */
    private String assignee;
    private String owner;
    private String description;
    private Date claimTime;
    private Date createTime;

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getProcessInstanceId() {
        return null;
    }

    @Override
    public String getExecutionId() {
        return null;
    }

    @Override
    public String getProcessDefinitionId() {
        return null;
    }

    @Override
    public String getTaskDefinitionKey() {
        return null;
    }

    @Override
    public Date getDueDate() {
        return null;
    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public String getParentTaskId() {
        return null;
    }

    @Override
    public String getTenantId() {
        return null;
    }

    @Override
    public String getFormKey() {
        return null;
    }

    @Override
    public Map<String, Object> getTaskLocalVariables() {
        return null;
    }

    @Override
    public Map<String, Object> getProcessVariables() {
        return null;
    }

}
