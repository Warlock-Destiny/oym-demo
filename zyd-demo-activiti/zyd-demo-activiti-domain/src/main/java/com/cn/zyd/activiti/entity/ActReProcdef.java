package com.cn.zyd.activiti.entity;

import com.cn.zyd.base.model.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *  
 */
@Data
@Accessors(fluent = true)
public class ActReProcdef extends BaseEntity {

    // 
    private String id;
    // 
    private Long rev;
    // 
    private String category;
    // 
    private String name;
    // 
    private String key;
    // 
    private Long version;
    // 
    private String deploymentId;
    // 
    private String resourceName;
    // 
    private String dgrmResourceName;
    // 
    private String description;
    // 
    private Long hasStartFormKey;
    // 
    private Long hasGraphicalNotation;
    // 
    private Long suspensionState;
    // 
    private String tenantId;
    // 
    private String engineVersion;
}
