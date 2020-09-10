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
