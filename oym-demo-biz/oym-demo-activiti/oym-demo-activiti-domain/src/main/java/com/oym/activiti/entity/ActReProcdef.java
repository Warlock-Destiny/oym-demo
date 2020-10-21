package com.oym.activiti.entity;

import com.oym.base.db.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *  
 */
@Data
@Accessors(fluent = true)
public class ActReProcdef implements BaseEntity {

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
