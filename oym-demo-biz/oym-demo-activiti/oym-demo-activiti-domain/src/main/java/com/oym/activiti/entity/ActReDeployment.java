package com.oym.activiti.entity;

import com.oym.base.db.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *  
 */
@Data
@Accessors(fluent = true)
public class ActReDeployment extends BaseEntity {

    // 
    private String id;
    // 
    private String name;
    // 
    private String category;
    // 
    private String key;
    // 
    private String tenantId;
    // 
    private java.sql.Timestamp deployTime;
    // 
    private String engineVersion;
}
