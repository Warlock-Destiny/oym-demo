package com.oym.activiti.dto;

import com.oym.base.web.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangyd
 * @date 2019/12/25 10:59
 * @desc
 */
@Data
@Accessors(chain = true)
@ApiModel("回退任务dto")
public class RevokeDto implements BaseDto {
    @ApiModelProperty("历史任务id")
    private String hisTaskId;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty(value = "任务参数", position = 3)
    private TaskParam taskParam;
}
