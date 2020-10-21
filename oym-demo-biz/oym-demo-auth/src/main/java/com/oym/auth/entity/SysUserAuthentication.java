package com.oym.auth.entity;

import com.oym.base.model.entity.BaseCommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysUserAuthentication extends BaseCommonEntity implements UserDetails {


    /**
     * 所属机构id
     */
    private Long orgId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 账户生效
     */
    private boolean accountNonExpired;

    /**
     * 账户锁定
     */
    private boolean accountNonLocked;

    /**
     * 凭证生效
     */
    private boolean credentialsNonExpired;

    /**
     * 激活状态
     */
    private boolean enable;

    /**
     * 删除状态（逻辑删除）
     */
    private boolean deleteStatus;

    /**
     * 权限列表
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 备注
     */
    private String remark;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enable;
    }
}
