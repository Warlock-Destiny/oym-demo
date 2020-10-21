package com.oym.auth.service;

import com.oym.auth.dao.AuthenticationDao;
import com.oym.auth.dao.GrantedAuthorityDao;
import com.oym.auth.entity.SysClientAuthentication;
import com.oym.auth.entity.SysGrantedAuthority;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
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
        if (client == null) {
            throw new NoSuchClientException("无效的客户端: " + clientId);
        }
        if (StringUtils.isNoneBlank(client.getAuthorizedGrantTypesStr())) {
            String[] strings = StringUtils.split(client.getAuthorizedGrantTypesStr(),",");
            Set<String> types = new HashSet<>(Arrays.asList(strings));
            client.setAuthorizedGrantTypes(types);
        }


        Collection<SysGrantedAuthority> sysGrantedAuthorities = grantedAuthorityDao.listGrantedByClientId(client.getId());
        client.setAuthorities(sysGrantedAuthorities);

        client.setClientSecret("{noop}" + client.getClientSecret());

        log.debug(client.toString());
        return client;
    }
}
