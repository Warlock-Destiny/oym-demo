package com.oym.component.util.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author zyd
 * @date 2019/11/11 11:31
 * @desc 此类可配合TreeUtil直接生成树
 */
@Data
@Accessors(chain = true)
public abstract class TreeModel<T, E extends TreeModel<T,E>> implements Serializable {
    @ApiModelProperty("标记")
    private T sign;
    @ApiModelProperty("父标记")
    private T pSign;
    @ApiModelProperty("子节点")
    private List<E> children;

}
