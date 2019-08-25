package com.dp.homework.config;

import com.dp.homework.shiro.OAuth2Realm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dp
 * @create 2019-08-25 1:49
 * shiro全局配置
 */

@Slf4j
@Configuration
public class ShiroConfig {


    private OAuth2Realm oAuth2Realm;

    @Bean("securityManager")
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm){
        this.oAuth2Realm = oAuth2Realm;
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm);
        log.info("---------------securityManager注入完成");
        return  securityManager;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        //配置登录的url的登录成功的url
        filterFactoryBean.setLoginUrl("/user/login");
        filterFactoryBean.setSuccessUrl("/showcenter");
        //配置未授权的跳转页面
        filterFactoryBean.setUnauthorizedUrl("/error/403");

        Map<String , String> map = new LinkedHashMap<>();
        map.put("/user/login","anon");  //anon 匿名访问
        map.put("/user/register","anon");
        map.put("/user*","user");  //登录后访问
        map.put("/user/*","user");
        map.put("/post/*","user");
        filterFactoryBean.setFilterChainDefinitionMap(map);
        return filterFactoryBean;
    }

}
