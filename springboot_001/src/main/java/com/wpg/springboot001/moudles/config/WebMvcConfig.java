package com.wpg.springboot001.moudles.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({WebMvcConfig.class})
public class WebMvcConfig {
@Value("${server.http.port}")
private int httpport;

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
}
