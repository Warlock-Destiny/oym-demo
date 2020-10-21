package com.oym.activiti.dto;

import com.oym.base.web.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangyd
 * @date 2019/11/5 10:08
 */
@Data
@Accessors(chain = true)
@ApiModel("结束任务dto")
public class CompleteTaskDto implements BaseDto {
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "任务id", position = 1)
    private String taskId;
    @ApiModelProperty(value = "评论", position = 2)
    private String comment;
    @ApiModelProperty(value = "任务参数", position = 3)
    private TaskParam taskParam;
}
