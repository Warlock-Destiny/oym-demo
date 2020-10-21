package com.oym.common.auth.dao;

import com.oym.base.db.dao.BaseDao;
import com.oym.common.auth.model.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @title: 权限操作数据库持久层
 */
@Repository
public interface PermissionDao extends BaseDao<Permission> {

    /**
     * @description 取的全部权限，初始化时用，使用ehcache缓存，每小时刷新一次权限
     */
//    @Cacheable(value = "permissionCache")
    List<Permission> findAll();

    /**
     * @description 匹配指定用户是否拥有权限(弃用，会从accessToken中获取用户角色)
     */
    List<Permission> findByUserId(Long userId);

}
