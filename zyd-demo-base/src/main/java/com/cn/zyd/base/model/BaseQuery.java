package com.cn.zyd.base.model;

import com.cn.zyd.base.constant.SortEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author zyd
 * @date 2019/10/27 0:35
 * @desc 查询抽象类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BaseQuery {
    protected int current;
    protected int size;

    public boolean isAsc(String sort) {
        return SortEnum.isAsc(sort);
    }

}
