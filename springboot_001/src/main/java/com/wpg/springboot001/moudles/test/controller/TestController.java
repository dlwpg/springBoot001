package com.wpg.springboot001.moudles.test.controller;

import com.wpg.springboot001.moudles.test.vo.ApplicationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class TestController {
    //日志打印
    private final static Logger LOGGER= LoggerFactory.getLogger(TestController.class);

    //测试获取application.properties数据(@Value的方式)
    @Value("${server.port}")
    private int port;
    @Value("${com.name}")
    private String name;
    @Value("${com.age}")
    private int age;
    @Value("${com.desc}")
    private String desc;
    @Value("${com.random}")
    private String random;

    /**
     * 192.168.18.232:8085/test/testProperties ===get
     */
    @GetMapping("/testProperties")
    @ResponseBody
    public String testProperties() {
        return new StringBuilder().append(port).append("-----")
                .append(name).append("-----")
                .append(age).append("-----")
                .append(desc).append("-----")
                .append(random).append("-----").toString();
    }


    //获取applicationtest.properties的属性
    @Autowired
    private ApplicationTest at;

    /**
     * 192.168.18.232:8085/test/testpropertiestest
     */
    @GetMapping("/testpropertiestest")
    @ResponseBody
    public String testpropertiestest(){
        StringBuilder sb=new StringBuilder();
        sb.append(at.getPort()).append("-----")
                .append(at.getName()).append("-----")
                .append(at.getAge()).append("-----")
                .append(at.getDesc()).append("-----")
                .append(at.getRandom()).append("-----");
        return sb.toString();
    }


    /**
     * 192.168.18.232:8085/test/logtest
     */
    //测试日志打印
    @GetMapping("/logtest")
    @ResponseBody
    public String logtest(){
        LOGGER.trace("this is trace log");
        LOGGER.debug("this is debug log");
        LOGGER.info("this is info log");
        LOGGER.warn("this is warn log");
        LOGGER.error("this is error log");
        return "this IS log test";
    }


    /**
     * @return 192.168.18.232:8080/test/test001
     */
    @GetMapping("/test001")
    @ResponseBody
    public String test001() {
        return "this is test 001.";
    }

}
