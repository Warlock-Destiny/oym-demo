package com.cn.zyd.activiti.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author zyd
 * @date 2019/10/9 17:16
 * @desc 流程启动实例
 */
@Data
public class ProcessStartDto {
    /**
     * 流程id
     */
    private String processDefinitionId;
    /**
     * 流程名称
     */
    private String processName;
    /**
     * 流程参数
     */
    private Map<String, Object> params;

}
