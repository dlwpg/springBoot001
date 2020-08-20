package com.wpg.springboot001.config;

import com.wpg.springboot001.filter.RequestParamFilter;
import com.wpg.springboot001.intercepter.RequestViewIntercepter;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {
@Value("${server.http.port}")
private int httpport;
@Autowired
private RequestViewIntercepter requestViewIntercepter;

    @Bean
    public Connector connector(){
        Connector connector=new Connector();
        //端口被443战用，给他一个http可以访问的端口
        connector.setPort(httpport);
        //设置访问头为http可访问
        connector.setScheme("http");
        return connector;
    }

    @Bean
    public ServletWebServerFactory servletWebServerFactory(){
        TomcatServletWebServerFactory tomcatServletWebServerFactory=new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addAdditionalTomcatConnectors(connector());
        return tomcatServletWebServerFactory;
    }

    //注入过滤器
    @Bean
    public FilterRegistrationBean<RequestParamFilter> register(){
        FilterRegistrationBean<RequestParamFilter> register=
                new FilterRegistrationBean<RequestParamFilter>();
        register.setFilter(new RequestParamFilter());
        return register;
    }

    //注入拦截器  实现拦截必须实现WebMvcConfigurer接口
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestViewIntercepter).addPathPatterns("/**");
    }
}
