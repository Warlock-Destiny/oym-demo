package com.oym.generate.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zyd
 * @date 2020/2/26 9:03
 * @desc
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "生成dto")
public class GenerateDto {
    @ApiModelProperty("表名")
    private String tables;
    @ApiModelProperty("包名")
    private String packName;
    @ApiModelProperty("模块名称")
    private String moduleName;

}
