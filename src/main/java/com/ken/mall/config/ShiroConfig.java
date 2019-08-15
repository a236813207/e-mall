package com.ken.mall.config;

import com.ken.mall.service.rbac.SysUserService;
import com.ken.mall.web.admin.auth.UserRealm;
import com.ken.mall.web.admin.auth.credentials.UserCredentialMatcher;
import com.ken.mall.web.filter.AuthFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ken
 * @date 2019/4/22
 * @description
 */
@Configuration
public class ShiroConfig {

    /**
     * shiro生命周期处理器
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public Realm shiroRealm() {
        UserRealm realm = new UserRealm();
        realm.setCredentialsMatcher(new UserCredentialMatcher());
        return realm;
    }

    @Bean(name = "sessionCookie")
    public Cookie sessionCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        simpleCookie.setHttpOnly(false);
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    @Bean(name = "rememberMeCoolie")
    public Cookie rememberMeCoolie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(86400000 * 5);
        return simpleCookie;
    }

    @Bean
    public RememberMeManager rememberMeManager(Cookie rememberMeCoolie) {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        rememberMeManager.setCookie(rememberMeCoolie);
        return rememberMeManager;
    }

    @Bean
    public SessionDAO sessionDAO() {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
        return sessionDAO;
    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO, Cookie sessionCookie) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionIdCookie(sessionCookie);
        sessionManager.setGlobalSessionTimeout(2 * 1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    @Bean
    public SecurityManager securityManager(Realm realm, SessionManager sessionManager, RememberMeManager rememberMeManager) {
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        //securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager);
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return new AuthorizationAttributeSourceAdvisor();
    }

    /**
     * springboot 会自动注册filter 导致shiro filter失效，帮删除filter的bean注册
     **/
    /*@Bean
    @Order(100)
    public FormAuthenticationFilter  formAuthenticationFilter(){
        FormAuthenticationFilter filter = new AuthFilter();
        filter.setUsernameParam("username");
        filter.setPasswordParam("password");
        filter.setLoginUrl("/login");
        filter.setSuccessUrl("/");
        return filter;
    }*/
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
                                                         MappingJackson2HttpMessageConverter jsonConverter , SysUserService sysUserService) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/admin/login");
        AuthFilter filter = new AuthFilter();
        filter.setUsernameParam("username");
        filter.setPasswordParam("password");
        filter.setLoginUrl("/admin/login");
        filter.setSuccessUrl("/");
        filter.setJsonConverter(jsonConverter);
        filter.setSysUserService(sysUserService);
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", filter);
        bean.setFilters(filters);
        setFilterChainDefinitions(bean);
        return bean;
    }

    private void setFilterChainDefinitions(ShiroFilterFactoryBean factoryBean) {
        Map<String, String> filterChainDefinitions = new LinkedHashMap<>();
        filterChainDefinitions.put("/swagger-ui.html","anon");
        filterChainDefinitions.put("/swagger-resources","anon");
        filterChainDefinitions.put("/v2/api-docs","anon");
        filterChainDefinitions.put("/admin/logout", "anon");
        filterChainDefinitions.put("/admin/**", "authc");
        filterChainDefinitions.put("/**", "anon");
        filterChainDefinitions.put("/job/**","anon");
        filterChainDefinitions.put("/static/*", "anon");

        factoryBean.setFilterChainDefinitionMap(filterChainDefinitions);
    }

    @Bean
    @Order(1)
    public FilterRegistrationBean delegatingFilterProxy() {
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(proxy);
        registrationBean.setAsyncSupported(true);
        registrationBean.setName("shiroFilter");
        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }
}
