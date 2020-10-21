package com.oym.activiti.dto;

import com.oym.base.web.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zhangyd
 * @date 2019/10/10 15:26
 * @desc
 */
@Data
@Accessors(chain = true)
@ApiModel("历史流程实例dto")
public class HistoricProcessInstanceDto implements BaseDto {
    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty(value = "流程实例名称", position = 1)
    private String name;
    @ApiModelProperty(value = "流程发起人", position = 2)
    private String startUserId;
    @ApiModelProperty(value = "开始时间", position = 3)
    private Date startTime;
    @ApiModelProperty(value = "流程结束时间", position = 4)
    private Date endTime;
    @ApiModelProperty(value = "流程定义id", position = 5)
    private String processDefinitionId;
    @ApiModelProperty(value = "流程的部署id", position = 6)
    private String deploymentId;

}
