package com.wpg.springboot001.moudles.test.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//引入局部配置
@PropertySource("classpath:config/applicationtest.properties")
//添加前缀不用@Value
@ConfigurationProperties(prefix = "com.qq")
public class ApplicationTest {
//    //1)获取applicationtest.properties里面的属性
//    @Value("${server.port1}")
//    private int port;
//    @Value("${com.namex}")
//    private String name;
//    @Value("${com.agex}")
//    private int age;
//    @Value("${com.descx}")
//    private String desc;
//    @Value("${com.randomx}")
//    private String random;


    //2)获取applicationtest.properties里面的属性
    // (添加前缀@ConfigurationProperties(prefix = "com.qq")就可以不用@Value了)
    private int port;
    private String name;
    private int age;
    private String desc;
    private String random;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
