package com.cn.generate.base.query;

import com.cn.component.web.model.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zyd
 * @date 2020/2/25 15:09
 * @desc
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "数据库表查询对象")
public class TableQuery extends BaseQuery {

    @ApiModelProperty("数据库名")
    private String tableSchema;
    @ApiModelProperty("数据库表名")
    private List<String> tableNameList;

}
