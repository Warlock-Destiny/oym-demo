package com.cn.zyd.common.util.model;

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
public abstract class TreeModel<T, E extends TreeModel> implements Serializable {
    private T id;
    private T pid;
    private List<E> children;

}
