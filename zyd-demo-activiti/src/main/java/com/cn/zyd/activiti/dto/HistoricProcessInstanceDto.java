package com.cn.zyd.activiti.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.activiti.engine.history.HistoricProcessInstance;

import java.util.Date;
import java.util.Map;

/**
 * @author zyd
 * @date 2019/10/10 15:26
 * @desc
 */
@Data
@Accessors(chain = true)
public class HistoricProcessInstanceDto implements HistoricProcessInstance {
    private String id;
    private String name;
    private String startUserId;
    private Date startTime;
    private Date endTime;
    private String processDefinitionId;
    private String deploymentId;

    @Override
    public String getBusinessKey() {
        return null;
    }

    @Override
    public String getProcessDefinitionName() {
        return null;
    }

    @Override
    public String getProcessDefinitionKey() {
        return null;
    }

    @Override
    public Integer getProcessDefinitionVersion() {
        return null;
    }

    @Override
    public Long getDurationInMillis() {
        return null;
    }

    @Override
    public String getEndActivityId() {
        return null;
    }

    @Override
    public String getStartActivityId() {
        return null;
    }

    @Override
    public String getDeleteReason() {
        return null;
    }

    @Override
    public String getSuperProcessInstanceId() {
        return null;
    }

    @Override
    public String getTenantId() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Map<String, Object> getProcessVariables() {
        return null;
    }
}
