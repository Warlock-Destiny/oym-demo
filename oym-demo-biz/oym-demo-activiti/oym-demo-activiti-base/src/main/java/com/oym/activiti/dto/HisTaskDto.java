package com.oym.activiti.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zyd
 * @date 2019/10/9 14:22
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@ApiModel("历史任务dto")
public class HisTaskDto extends TaskDto {
    @ApiModelProperty(value = "开始时间", position = 1)
    private Date startTime;
    @ApiModelProperty(value = "结束时间", position = 2)
    private Date endTime;
    @ApiModelProperty(value = "时间", position = 3)
    private Date time;
    @ApiModelProperty(value = "间隔时间", position = 4)
    private Long durationInMillis;
    @ApiModelProperty(value = "任务持续时间", position = 5)
    private Long workTimeInMillis;
    @ApiModelProperty(value = "删除理由", position = 6)
    private String deleteReason;
}
