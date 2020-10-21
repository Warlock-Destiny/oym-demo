package com.oym.base.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oym.base.model.entity.BaseEntity;

/**
 * @author zhangyd
 * @date 2019/10/20 17:48
 * @desc
 */
public interface BaseDao<T extends BaseEntity> extends BaseMapper<T> {
}
