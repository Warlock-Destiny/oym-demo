package com.oym.common.auth.service;

import com.oym.common.auth.dao.PermissionDao;
import com.oym.common.auth.model.Permission;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @title: 权限获取数据源
 */
@Service
@Slf4j
public class PermissionSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * @description 加载所有权限
     */
    private HashMap<String, Collection<ConfigAttribute>> loadResourceDefine() {
        //当权限进行变更时使用以下注解清除缓存
        //@CacheEvict(value="permissionCache",allEntries=true)
        HashMap<String, Collection<ConfigAttribute>> map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        List<Permission> permissions = permissionDao.findAll();

        for (Permission permission : permissions) {
            if (StringUtils.isEmpty(permission.getUrl())) {
                continue;
            }
            array = map.get(permission.getUrl());

            if (array == null) {
                array = new ArrayList<>();
            }

            cfg = new SecurityConfig(permission.getName());
            //此处添加的信息将会作为PermissionDecisionService类的decide的第三个参数。
            array.add(cfg);
            //用权限的getUrl()作为map的key，角色列表的集合作为 value，
            map.put(permission.getUrl(), array);
        }

        return map;
    }


    /**
     * @description 从decide方法中请求本方法，方法返回该URL附带的角色权限集合
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        HashMap<String, Collection<ConfigAttribute>> map = loadResourceDefine();

        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        AntPathRequestMatcher matcher;

        for (String s : map.keySet()) {
            matcher = new AntPathRequestMatcher(s);

            if (matcher.matches(request)) {
                return map.get(s);
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
}
