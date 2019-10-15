//package com.cn.zyd.activiti.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//
///**
// * @author linys
// * @title: 自定义鉴权决策服务
// * @package: com.ctff.cloud.authcommon.service
// * @className: AccessDecisionService
// * @update version     author      date                  description
// * -----------------------------------------------------------------
// * 1.0.0       linys       2019-07-23 9:57       自定义鉴权决策服务
// */
//@Service
//@Slf4j
//public class AccessDecisionService implements AccessDecisionManager {
//
//    /**
//     * @param authentication 是从AuthenticationUserDetailService中循环添加到 GrantedAuthority 对象中的权限信息集合.
//     * @param o              包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
//     * @param collection     为PermissionSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，此方法是为了判定用户请求的url
//     *                       是否在权限表中.如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
//     * @return void
//     * @throws AccessDeniedException
//     * @throws InsufficientAuthenticationException
//     * @methodName decide
//     * @author linys
//     * @date 2019-07-23 9:58
//     * @description 方法是判定是否拥有权限的决策方法，
//     */
//    @Override
//    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
//        //如果数据库中没有这个URL的相关权限则放行
//        if (null == collection || collection.size() <= 0) {
//            return;
//        }
//        for (ConfigAttribute configAttribute : collection) {
//            String role = configAttribute.getAttribute();
//            if ("ROLE_ILLEGAL".equals(role.trim())) {
//                log.debug("ADService:禁止访问,URL未定义或未与角色绑定");
//                throw new AccessDeniedException("ADService:禁止访问,URL未定义或未与角色绑定");
//            }
//            if ("ROLE_ANY".equals(role.trim())) {
//                log.debug("ADService:白名单地址");
//                return;
//            }
//            for (GrantedAuthority ga : authentication.getAuthorities()) {
//                //authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
//                log.debug("需要角色:{}, 存在角色:{}", role, ga.getAuthority());
//                if (role.trim().equals(ga.getAuthority())) {
//                    return;
//                }
//            }
//        }
//        log.debug("ADService:无权限");
//        throw new AccessDeniedException("ADService:无权限");
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute configAttribute) {
//        return true;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
