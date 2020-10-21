package com.oym.activiti.dto;

import com.oym.base.web.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangyd
 * @date 2019/10/9 17:16
 * @desc 流程启动实例
 */
@Data
@Accessors(chain = true)
@ApiModel("流程启动参数")
public class ProcessStartDto implements BaseDto {
    @ApiModelProperty(value = "流程key", position = 1)
    private String processDefinitionKey;
    @ApiModelProperty(value = "流程名称", position = 2)
    private String processName;
    @ApiModelProperty(value = "业务的key", position = 3)
    private String businessKey;
    @ApiModelProperty(value = "启动流程所带的参数", position = 4)
    private TaskParam taskParam;

}
