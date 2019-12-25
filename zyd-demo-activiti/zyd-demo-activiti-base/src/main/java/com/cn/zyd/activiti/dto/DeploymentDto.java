package com.cn.zyd.activiti.dto;

import com.cn.zyd.base.model.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zyd
 * @date 2019/10/8 8:33
 * @desc
 */
@Data
@Accessors(chain = true)
@ApiModel("部署dto")
public class DeploymentDto implements BaseDto {
    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty(value = "部署名称", position = 1)
    private String name;
    @ApiModelProperty(value = "部署时间", position = 2)
    private Date deploymentTime;
    @ApiModelProperty(value = "bpmnxml", position = 3)
    private String bpmnXml;

}
