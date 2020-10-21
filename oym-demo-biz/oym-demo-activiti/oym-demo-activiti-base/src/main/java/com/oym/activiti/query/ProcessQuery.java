package com.oym.activiti.query;

import com.oym.base.web.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangyd
 * @date 2019/11/1 0:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("流程查询类")
public class ProcessQuery extends BaseQuery {

    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("是否结束")
    private Boolean finish;

    @ApiModelProperty("根据流程实例评论详情")
    private String processInstanceId;
}
