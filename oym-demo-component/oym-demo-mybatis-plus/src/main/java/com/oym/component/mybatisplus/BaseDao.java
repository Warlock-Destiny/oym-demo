package com.oym.component.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.oym.component.mybatisplus.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 基础dao
 *
 * @author Caiyw
 */
public interface BaseDao<T extends BaseEntity> extends BaseMapper<T> {

    /**
     * 根据 ID 修改
     *
     * @param entity 实体对象
     */
    int updateAllById(@Param(Constants.ENTITY) T entity);

    /**
     * 批量插入
     */
    void batchInsert(List<T> list);

}
