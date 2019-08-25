package com.dp.homework.shiro;

import com.dp.homework.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dp
 * @create 2019-08-25 1:51
 */
@Slf4j
@Component
public class OAuth2Realm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        AccountProfile profile = userService.login(token.getUsername(), String.valueOf(token.getPassword()));

        log.info("---------------->进入认证步骤");
        //token.getCredentials()密码  getName当前realm的名称
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(profile,token.getCredentials(),getName());
        return authenticationInfo;
    }
}
