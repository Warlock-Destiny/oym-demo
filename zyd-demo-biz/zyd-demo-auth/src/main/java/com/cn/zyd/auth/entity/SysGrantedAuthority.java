package com.cn.zyd.auth.entity;

import com.cn.zyd.base.model.entity.BaseCommonEntity;
import com.cn.zyd.base.model.entity.BaseEntity;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 *
 */
@ToString
public class SysGrantedAuthority extends BaseCommonEntity implements GrantedAuthority, Serializable {

    private static final long serialVersionUID = 5698641074914331015L;

    /**
     * 权限
     */
    private String authority;

    /**
     * 权限
     *
     * @return authority
     */
    @Override
    public String getAuthority() {
        return authority;
    }

    /**
     * 权限
     *
     * @param authority 权限
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

}