package com.wpg.graduationdesign.config.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    @Bean
    public DefaultSecurityManager defaultSecurityManager() {
        DefaultWebSecurityManager SecurityManager = new DefaultWebSecurityManager();
        SecurityManager.setRealm(myRealm);
        //设置记住我
        SecurityManager.setRememberMeManager(rememberMeManager());
        SecurityManager.setSessionManager(sessionManager());
        return SecurityManager;
    }

    /**
     * 配置shiro过滤器工厂
     * -----------------
     * 拦截权限
     * anon：匿名访问，无需登录
     * authc：登录后才能访问
     * user：登录过能访问
     * logout：登出
     * roles：角色过滤器
     * ------------------
     * URL匹配风格
     * ?：匹配一个字符，如 /admin? 将匹配 /admin1，但不匹配 /admin 或 /admin/
     * *：匹配零个或多个字符串，如 /admin* 将匹配 /admin 或/admin123，但不匹配 /admin/1
     * **：匹配路径中的零个或多个路径，如 /admin/** 将匹配 /admin/a 或 /admin/a/b
     * -----------------------
     * 方法名不能乱写，如果我们定义为别的名称，又没有添加注册过滤器的配置，那么shiro会加载ShiroWebFilterConfiguration过滤器，
     * 该过滤器会寻找shiroFilterFactoryBean，找不到会抛出异常
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager());

        shiroFilterFactoryBean.setLoginUrl("/nxzm/user/login");
        shiroFilterFactoryBean.setSuccessUrl("/nxzm/index");

        Map<String, String> map = new LinkedHashMap<>();
        //不用登陆也能访问到的路径
        //默认定位到static下
        map.put("/", "authc");
        map.put("/static/**", "anon");
        map.put("/js/**", "anon");
        map.put("/css/**", "anon");
        map.put("/fonts/**", "anon");
        map.put("/images/**", "anon");
        map.put("/plugin/**", "anon");
        map.put("/upload/**", "anon");
        map.put("/nxzm/user/login", "anon");
        map.put("/shone/login", "anon");
        map.put("/shone/register", "anon");
        map.put("/nxzm/user/activation/*", "anon");
        map.put("/nxzm/user/register", "anon");
        map.put("/nxzm/user/logout", "logout");

        map.put("/shone/review/reviews", "anon");


        //前台路径
        map.put("/nxzmqt/mainpage", "anon");
        map.put("/qtjtwj/**", "anon");





        //如果必须要登陆
        map.put("/nxzm/**", "authc");
        map.put("/shone/**", "authc");



//        //如果设置记住我
//        map.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;

    }

    /**
     * 注册shiro方言，让thymeleaf支持shiro标签
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 自动代理类，支持Shiro的注解
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator =
                new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultSecurityManager());
        return authorizationAttributeSourceAdvisor;
    }

    //    记住我remembermecookie
    @Bean
    public SimpleCookie remmemberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMeCookie");
        //如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，
        // 使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        simpleCookie.setHttpOnly(true);
        //设置cookie记住时间
        simpleCookie.setMaxAge(30 * 24 * 60 * 60);
        return simpleCookie;
    }

    //记住我的cookie管理器
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(remmemberMeCookie());
        //设置cookie的简单加密
        byte[] passwordCookie = Base64.decode("wupengguishizuilihaidebanchengdaniu");
        rememberMeManager.setCipherService(new AesCipherService());
        rememberMeManager.setCipherKey(passwordCookie);
        return rememberMeManager;
    }

    //sessoincookie
    public SimpleCookie sessionCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("shiroCoockie");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(30 * 24 * 60 * 60);
        return simpleCookie;
    }

    //session 管理，去掉重定向后Url追加SESSIONID
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookie(sessionCookie());
        return sessionManager;
    }
}
