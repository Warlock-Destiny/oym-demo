package com.oym.auth.entity;

import com.oym.base.db.entity.BaseCommonEntity;
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