package com.cn.zyd.auth.service;

import com.cn.zyd.auth.dao.AuthenticationDao;
import com.cn.zyd.auth.dao.GrantedAuthorityDao;
import com.cn.zyd.auth.entity.SysClientAuthentication;
import com.cn.zyd.auth.entity.SysGrantedAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import java.util.Collection;

/**
 */
@Slf4j
public class AuthenticationClientDetailService implements ClientDetailsService {

    @Autowired
    private AuthenticationDao authenticationDao;

    @Autowired
    private GrantedAuthorityDao grantedAuthorityDao;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.debug("拟登录客户端：" + clientId);

        SysClientAuthentication client = authenticationDao.getClientByClientId(clientId);

        if(client == null) {
            throw new NoSuchClientException("无效的客户端: " + clientId);
        }

        Collection<SysGrantedAuthority> sysGrantedAuthorities = grantedAuthorityDao.listGrantedByClientId(client.getId());
        client.setAuthorities(sysGrantedAuthorities);

        client.setClientSecret("{noop}" + client.getClientSecret());

        log.debug(client.toString());
        return client;
    }
}
