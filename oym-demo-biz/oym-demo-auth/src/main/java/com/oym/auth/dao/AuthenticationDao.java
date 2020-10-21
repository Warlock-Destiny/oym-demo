package com.oym.auth.dao;

import com.oym.auth.entity.SysClientAuthentication;
import com.oym.auth.entity.SysUserAuthentication;
import org.apache.ibatis.annotations.Mapper;

/**
 */
@Mapper
public interface AuthenticationDao {

    /**
     * @description 根据用户名获得用户信息，用户名要求唯一
     */
    SysUserAuthentication getUserByUsername(String username);

    /**
     * @description 根据客户端ID获得客户端信息，客户端ID要求唯一
     */
    SysClientAuthentication getClientByClientId(String clientId);
}
