package com.cn.zyd.activiti.dto;


import lombok.Data;
import lombok.experimental.Accessors;
import org.activiti.engine.repository.Deployment;

import java.util.Date;

/**
 * @author zyd
 * @date 2019/10/8 8:33
 * @desc
 */
@Data
@Accessors(chain = true)
public class DeploymentDto implements Deployment {
    /**
     * 部署主键id
     */
    private String id;
    /**
     * 部署名称
     */
    private String name;
    /**
     * 部署时间
     */
    private Date deploymentTime;
    /**
     * 具体xml
     */
    private String bpmnXml;

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getTenantId() {
        return null;
    }
}
