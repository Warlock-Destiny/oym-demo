package com.cn.zyd.activiti.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.activiti.engine.repository.ProcessDefinition;

/**
 * @author zyd
 * @date 2019/10/8 10:44
 * @desc 可执行流程
 */
@Data
@Accessors(chain = true)
public class ProcessDto implements ProcessDefinition {
    private String id;
    private String name;
    private String key;
    private String description;
    private String deploymentId;

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public String getResourceName() {
        return null;
    }

    @Override
    public String getDiagramResourceName() {
        return null;
    }

    @Override
    public boolean hasStartFormKey() {
        return false;
    }

    @Override
    public boolean hasGraphicalNotation() {
        return false;
    }

    @Override
    public boolean isSuspended() {
        return false;
    }

    @Override
    public String getTenantId() {
        return null;
    }

    @Override
    public String getEngineVersion() {
        return null;
    }
}
