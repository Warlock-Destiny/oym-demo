package com.cn.zyd.activiti.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 *  
 */
@Data
@Accessors(fluent = true)
public class ActReDeployment implements Serializable {

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
