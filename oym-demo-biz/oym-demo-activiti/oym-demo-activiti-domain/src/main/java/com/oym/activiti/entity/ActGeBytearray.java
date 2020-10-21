package com.oym.activiti.entity;

import com.oym.base.db.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *  
 */
@Data
@Accessors(fluent = true)
public class ActGeBytearray implements BaseEntity {

    // 
    private String id;
    // 
    private Long rev;
    // 
    private String name;
    // 
    private String deploymentId;
    // 
    private String bytes;
    // 
    private Long generated;
}
