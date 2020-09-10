package com.wpg.graduationdesign.config;

import com.wpg.graduationdesign.intercepter.RequestViewIntercepter;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebConfig implements WebMvcConfigurer {
    @Value("${server.http.port}")
    private int httpport;

    @Autowired
    private RequestViewIntercepter requestViewIntercepter;

    @Bean
    public Connector connector() {
        Connector connector = new Connector();
        connector.setPort(httpport);
        connector.setScheme("http");
        return connector;
    }

    @Bean
    public ServletWebServerFactory servletWebServerFactory(){
        TomcatServletWebServerFactory t=new TomcatServletWebServerFactory();
        t.addAdditionalTomcatConnectors(connector());
        return t;
    }

    //注入拦截器  实现拦截必须实现WebMvcConfigurer接口
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestViewIntercepter).addPathPatterns("/**");
    }
}
