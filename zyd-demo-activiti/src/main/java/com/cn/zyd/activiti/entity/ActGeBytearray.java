package com.cn.zyd.activiti.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *  
 */
@Data
@Accessors(fluent = true)
public class ActGeBytearray implements Serializable {

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
