package com.cn.zyd.auth.dao;

import com.cn.zyd.auth.entity.SysGrantedAuthority;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

/**
 */
@Mapper
public interface GrantedAuthorityDao {

    /**
     * @description 根绝用户ID获取该用户所拥有的角色列表
     */
    Collection<SysGrantedAuthority> listGrantedByUserId(Long userId);

    /**
     * @description 根据客户端ID获取该客户端所具备的角色列表
     */
    Collection<SysGrantedAuthority> listGrantedByClientId(Long clientId);
}
