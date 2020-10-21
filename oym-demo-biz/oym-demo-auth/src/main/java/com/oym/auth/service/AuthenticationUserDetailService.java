package com.oym.auth.service;

import com.oym.auth.dao.AuthenticationDao;
import com.oym.auth.dao.GrantedAuthorityDao;
import com.oym.auth.entity.SysGrantedAuthority;
import com.oym.auth.entity.SysUserAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;


/**
 */
@Slf4j
public class AuthenticationUserDetailService implements UserDetailsService {

    @Autowired
    private AuthenticationDao authenticationDao;

    @Autowired
    private GrantedAuthorityDao grantedAuthorityDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("拟登录用户：" + username);

        SysUserAuthentication user = authenticationDao.getUserByUsername(username);

        if (user == null){
            return null;
        }

        Collection<SysGrantedAuthority> sysGrantedAuthorities = grantedAuthorityDao.listGrantedByUserId(user.getId());
        user.setAuthorities(sysGrantedAuthorities);

        log.info("---------------------------------------------");
        log.info(user.toString());
        log.info("---------------------------------------------");
        return user;
    }
}
