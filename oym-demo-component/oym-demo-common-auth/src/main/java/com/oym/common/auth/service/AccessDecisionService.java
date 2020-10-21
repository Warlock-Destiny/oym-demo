package com.oym.common.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @title: 自定义鉴权决策服务
 */
@Service
@Slf4j
public class AccessDecisionService implements AccessDecisionManager {

    /**
     * @description 方法是判定是否拥有权限的决策方法，
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //如果数据库中没有这个URL的相关权限则放行
        if (null == collection || collection.size() <= 0) {
            return;
        }

        ConfigAttribute configAttribute;
        String needRole;

        for (ConfigAttribute attribute : collection) {
            configAttribute = attribute;
            needRole = configAttribute.getAttribute();
            if ("ROLE_ILLEGAL".equals(needRole.trim())) {
                log.debug("ADService:禁止访问,URL未定义或未与角色绑定");
                throw new AccessDeniedException("ADService:禁止访问,URL未定义或未与角色绑定");
            }

            if ("ROLE_ANY".equals(needRole.trim())) {
                log.debug("ADService:白名单地址");
                return;
            }

            for (GrantedAuthority ga : authentication.getAuthorities()) {
                //authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
                log.debug("需要角色:{}, 存在角色:{}", needRole, ga.getAuthority());

                if (needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        log.debug("ADService:无权限");
        throw new AccessDeniedException("ADService:无权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
