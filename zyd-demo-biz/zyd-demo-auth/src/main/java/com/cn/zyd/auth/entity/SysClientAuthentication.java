package com.cn.zyd.auth.entity;

import com.cn.zyd.base.model.entity.BaseCommonEntity;
import com.cn.zyd.base.model.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysClientAuthentication extends BaseCommonEntity implements ClientDetails {


    /**
     * 客户端名称
     */
    private String clientId;

    /**
     * 客户端别称
     */
    private String clientName;

    /**
     * 客户端可访问资源列表文本
     */
    private String resourceIdStr;

    /**
     * 客户端可访问资源列表
     */
    private Set<String> resourceIds;

    /**
     * 客户端秘钥
     */
    private String clientSecret;

    /**
     * 客户端访问范围文本
     */
    private String scopeStr;

    /**
     * 客户端访问范围
     * read,write,trust
     */
    private Set<String> scope;

    /**
     * 客户端适配授权类型文本
     */
    private String authorizedGrantTypesStr;

    /**
     * 客户端适配授权类型
     * authorization_code,password,refresh_token,implicit,client_credentials
     */
    private Set<String> authorizedGrantTypes;

    /**
     * authorization_code模式下回调url，允许配置多个
     */
    private Set<String> webServerRedirectUri;

    /**
     * 授予角色
     * 数据库自定义角色
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * accessToken有效期，单位秒
     */
    private Integer accessTokenValidity;

    /**
     * refreshToken有效期，单位秒
     */
    private Integer refreshTokenValidity;

    /**
     * 预留字段，格式json
     */
    private Map<String, Object> additionalInformation;

    /**
     * authorization_code模式下登录成功后会提示用户授权
     * 'true','false', 'read','write'.
     */
    private Set<String> autoapprove;


    private Set<String> registeredRedirectUri;

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null && !this.clientSecret.isEmpty();
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return (Collection<GrantedAuthority>) this.authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValidity;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if (this.autoapprove == null) {
            return false;
        } else {
            Iterator iterator = this.autoapprove.iterator();

            String auto;
            do {
                if (!iterator.hasNext()) {
                    return false;
                }

                auto = (String)iterator.next();
            } while(!auto.equals("true") && !scope.matches(auto));

            return true;
        }
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }

}
