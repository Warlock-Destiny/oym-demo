package com.cn.zyd.activiti.dto;

import com.cn.zyd.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Map;

/**
 * @author zyd
 * @date 2019/10/8 11:09
 */
@Data
@Accessors(chain = true)
@ApiModel("任务dto参数")
public class TaskDto implements BaseDto {

    @ApiModelProperty(value = "任务id", position = 1)
    private String id;
    @ApiModelProperty(value = "任务名称", position = 2)
    private String name;
    @ApiModelProperty(value = "任务对应的流程实例id", position = 3)
    private String processInstanceId;
    @ApiModelProperty(value = "任务委托人", position = 4)
    private String assignee;
    @ApiModelProperty(value = "任务拥有者", position = 5)
    private String owner;
    @ApiModelProperty(value = "任务类型", position = 6)
    private String category;
    @ApiModelProperty(value = "任务描述", position = 7)
    private String description;
    @ApiModelProperty(value = "任务声明时间", position = 8)
    private Date claimTime;
    @ApiModelProperty(value = "任务创建时间", position = 9)
    private Date createTime;
    @ApiModelProperty(value = "任务带的参数", position = 10)
    private Map<String, Object> taskLocalVariables;
    @ApiModelProperty(value = "流程带的参数", position = 11)
    private Map<String, Object> processVariables;


}
