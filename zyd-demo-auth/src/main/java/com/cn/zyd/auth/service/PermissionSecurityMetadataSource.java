package com.cn.zyd.auth.service;

import com.cn.zyd.auth.model.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author linys
 * @title: 权限获取数据源
 * @package: com.ctff.cloud.authcommon.service
 * @className: PermissionSecurityMetadataSource
 * @update version     author      date                  description
 * -----------------------------------------------------------------
 * 1.0.0       linys       2019-07-23 10:12      权限获取数据源初始化
 */
@Service
@Slf4j
public class PermissionSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * @param o 传如的httpRequest，取url
     * @return java.util.Collection<org.springframework.security.access.ConfigAttribute>
     * @throws IllegalArgumentException
     * @methodName getAttributes
     * @author linys
     * @date 2019-07-23 10:17
     * @description 从decide方法中请求本方法，方法返回该URL附带的角色权限集合
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        Map<String, Collection<ConfigAttribute>> map = loadResourceDefine();

        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher(entry.getKey());
            if (matcher.matches(request)) {
                return entry.getValue();
            }
        }
        /**
         * 防止数据库中没有数据或没有匹配权限，不能进行权限拦截
         * 若返回null则不执行决策Service中的decide方法，踩坑！！
         */
        Collection<ConfigAttribute> array = new ArrayList<>();
        ConfigAttribute configAttribute = new SecurityConfig("ROLE_ILLEGAL");
        array.add(configAttribute);
        return array;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * @return java.util.HashMap<java.lang.String, java.util.Collection < org.springframework.security.access.ConfigAttribute>>
     * @throws
     * @methodName loadResourceDefine
     * @author linys
     * @date 2019-07-23 10:13
     * @description 加载所有权限
     */
    private Map<String, Collection<ConfigAttribute>> loadResourceDefine() {
        //当权限进行变更时使用以下注解清除缓存
        //@CacheEvict(value="permissionCache",allEntries=true)
        List<Permission> permissions = getPermissionList();
        Map<String, Collection<ConfigAttribute>> map = new HashMap<>();
        for (Permission permission : permissions) {
            Collection<ConfigAttribute> urlSet = map.computeIfAbsent(permission.getUrl(), x -> new HashSet<>());
            urlSet.add(new SecurityConfig(permission.getName()));
        }
        return map;
    }

    private List<Permission> getPermissionList() {
        List<Permission> permissionList = new ArrayList<>();
        permissionList.add(new Permission().setName("admin").setUrl("/aa"));
        permissionList.add(new Permission().setName("admin").setUrl("/bb"));
        permissionList.add(new Permission().setName("zyd").setUrl("/aa"));
        return permissionList;
    }


}
