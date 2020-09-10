package com.cn.generate.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author zyd
 * @date 2020/2/25 15:05
 * @desc
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "返回数据库表dto")
public class TableDto {
    @ApiModelProperty(value = "数据库表名", position = 0)
    private String tableName;
    @ApiModelProperty(value = "数据库引擎", position = 0)
    private String engine;
    @ApiModelProperty(value = "数据库备注", position = 0)
    private String tableComment;
    @ApiModelProperty(value = "创建时间", position = 0)
    private Date createTime;
}
