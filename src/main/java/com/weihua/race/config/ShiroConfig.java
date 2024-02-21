package com.weihua.race.config;


import com.weihua.race.shiro.JWTCredentialsMatcher;
import com.weihua.race.shiro.MultiRealmAuthenticator;
import com.weihua.race.shiro.NoSessionSubjectFactory;
import com.weihua.race.shiro.filter.AnyRolesAuthorizationFilter;
import com.weihua.race.shiro.filter.AuthParamFilter;
import com.weihua.race.shiro.filter.JWTFilter;
import com.weihua.race.shiro.realm.JWTRealm;
import com.weihua.race.shiro.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

/**
 * @author liulingyu
 * @description: shiro配置
 * @date 2022-05-06 21:10
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * @return com.hqully.labmanage.shiro.NoSessionSubjectFactory
     * @description: 配置禁用session的shiro工厂类
     * @date 2022/5/7 13:11
     * @author liulingyu
     */
    @Bean
    public NoSessionSubjectFactory noSessionSubjectFactory() {
        return new NoSessionSubjectFactory();
    }

    /**
     * 为 Spring-Bean 开启对 Shiro 注解的支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator app = new DefaultAdvisorAutoProxyCreator();
        app.setProxyTargetClass(true);
        return app;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/login");
        bean.setUnauthorizedUrl("/unAuthorized");
        HashMap<String, Filter> filter = new HashMap<>(16);
        filter.put("jwt", new JWTFilter());
        filter.put("anyRoles", new AnyRolesAuthorizationFilter());
        filter.put("param", new AuthParamFilter());


        bean.setFilters(filter);


        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/api-docs/**", "anon");
        filterMap.put("/webjars/springfox-swagger-ui/**", "anon");


        filterMap.put("/api/login", "anon");
        filterMap.put("/api/get-info", "anon");
        filterMap.put("/api/unauthorized/**", "anon");

        //        filterMap.put("/lab-manager/**", "jwt,roles[admin]");
        //        filterMap.put("/unit/find-all-unit", "jwt,anyRoles[admin,labManager]");
        //        filterMap.put("/unit/**", "jwt,roles[admin]");
        filterMap.put("/api/image/**", "anon");
        filterMap.put("/api/**", "jwt,param");


        bean.setFilterChainDefinitionMap(filterMap);


        return bean;
    }


    @Bean
    public UserRealm userRealm() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密方式
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //加密次数
        hashedCredentialsMatcher.setHashIterations(2);

        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }

    @Bean
    public JWTRealm jwtRealm() {
        JWTRealm jwtRealm = new JWTRealm();
        jwtRealm.setCredentialsMatcher(new JWTCredentialsMatcher());
        return jwtRealm;
    }

    /**
     * 配置 ModularRealmAuthenticator
     */
    /**
     * <p>自定义ModularRealmAuthenticator,处理多Realm情况下异常抛出问题</p>
     *
     * @param
     * @return org.apache.shiro.authc.pam.ModularRealmAuthenticator
     * @date 2022/5/7 16:20 <br>
     * @author liulingyu <br>
     */
    @Bean
    public ModularRealmAuthenticator authenticator() {
        ModularRealmAuthenticator authenticator = new MultiRealmAuthenticator();
        // 设置多 Realm的认证策略，默认 AtLeastOneSuccessfulStrategy
        AuthenticationStrategy strategy = new FirstSuccessfulStrategy();

        authenticator.setAuthenticationStrategy(strategy);
        return authenticator;
    }


    /**
     * <p>禁用session, 不保存用户登录状态。保证每次请求都重新认证</p>
     *
     * @param
     * @return org.apache.shiro.mgt.SessionStorageEvaluator
     * @date 2022/5/7 16:16 <br>
     * @author liulingyu <br>
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }

    /**
     * <p>自定义shiro安全管理器</p>
     *
     * @param
     * @return org.apache.shiro.mgt.SecurityManager
     * @date 2022/5/7 16:17 <br>
     * @author liulingyu <br>
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setAuthenticator(authenticator());
        securityManager.setSubjectFactory(noSessionSubjectFactory());

        List<Realm> realms = new ArrayList<Realm>(16);
        realms.add(jwtRealm());
        realms.add(userRealm());
        securityManager.setRealms(realms);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator());
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }


}
