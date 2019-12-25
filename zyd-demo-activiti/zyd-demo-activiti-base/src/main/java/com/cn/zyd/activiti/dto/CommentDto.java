package com.cn.zyd.activiti.dto;

import com.cn.zyd.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zyd
 * @date 2019/10/10 15:51
 */
@Data
@Accessors(chain = true)
@ApiModel("评论dto")
public class CommentDto implements BaseDto {

    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty(value = "用户id", position = 1)
    private String userId;
    @ApiModelProperty(value = "任务id", position = 2)
    private String taskId;
    @ApiModelProperty(value = "流程实例id", position = 3)
    private String processInstanceId;
    @ApiModelProperty(value = "时间点", position = 4)
    private Date time;
    @ApiModelProperty(value = "具体信息", position = 5)
    private String fullMessage;
}
