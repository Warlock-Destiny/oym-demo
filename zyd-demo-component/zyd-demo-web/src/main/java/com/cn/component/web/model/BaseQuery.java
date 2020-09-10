package com.cn.component.web.model;

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
    protected Integer current = 0;
    protected Integer size = 10;

}
