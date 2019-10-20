package com.cn.zyd.base.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author zhangyd
 * @date 2019/10/20 17:48
 * @desc
 */
public interface BaseDao<T> extends BaseMapper<T> {

    T getById(Long id);
}
