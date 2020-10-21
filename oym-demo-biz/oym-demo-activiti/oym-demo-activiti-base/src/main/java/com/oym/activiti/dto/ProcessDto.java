package com.oym.activiti.dto;

import com.oym.base.web.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangyd
 * @date 2019/10/8 10:44
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "流程dto")
public class ProcessDto implements BaseDto {
    @ApiModelProperty(value = "主键id", position = 1)
    private String id;
    @ApiModelProperty(value = "流程名称", position = 2)
    private String name;
    @ApiModelProperty(value = "流程key", position = 3)
    private String key;
    @ApiModelProperty(value = "流程描述", position = 4)
    private String description;
    @ApiModelProperty(value = "流程部署id", position = 5)
    private String deploymentId;


    @ApiModelProperty(value = "流程实例id", position = 6)
    private String processInstanceId;
    @ApiModelProperty(value = "流程关闭原因", position = 7)
    private String reason;


}
