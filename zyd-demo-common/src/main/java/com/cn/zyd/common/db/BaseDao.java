package com.cn.zyd.common.db;

import java.util.List;

/**
 * @author zyd
 * @date 2019/9/26 15:34
 * @desc
 */
public interface BaseDao<T> {
    /**
     * 插入实体类
     *
     * @param t 实体类
     * @return 插入主键
     */
    Long insert(T t);
    /**
     * 批量插入
     */
    void batchInsert(List<T> list);

    /**
     * 根据id查询
     *
     * @param id 主键id
     * @return 实体类
     */
    T selectById(Long id);

    /**
     * 根据id进行更新
     * @param t 实体类
     * @return 更新的数量
     */
    Integer updateById(T t);

    /**
     * 根据id进行部分更新
     * @param t 实体类
     * @return 更新的数量
     */
    Integer updateSelectById(T t);
}
