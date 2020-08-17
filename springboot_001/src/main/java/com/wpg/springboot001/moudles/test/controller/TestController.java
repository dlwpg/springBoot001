package com.wpg.springboot001.moudles.test.controller;

import com.wpg.springboot001.moudles.test.pojo.City;
import com.wpg.springboot001.moudles.test.pojo.Country;
import com.wpg.springboot001.moudles.test.service.CityService;
import com.wpg.springboot001.moudles.test.service.CountryService;
import com.wpg.springboot001.moudles.test.vo.ApplicationTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

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
     * @return 192.168.18.232/test/welcome
     */
    @GetMapping("/welcome")
    public String test001() {
        return "welcome";
    }

    @Autowired
    private CityService cityService;
    @Autowired
    private CountryService countryService;

    /**
     * 127.0.0.1/test/index
     */
    @GetMapping("/index")
    public String testIndexPage(ModelMap modelMap) {
        int countryId = 522;
        List<City> cities = cityService.getCitiesByCountryId(countryId);
        cities = cities.stream().limit(10).collect(Collectors.toList());
        Country country = countryService.getCountryByCountryId(countryId);

        modelMap.addAttribute("thymeleafTitle", "scdscsadcsacd");
        modelMap.addAttribute("checked", true);
        modelMap.addAttribute("currentNumber", 99);
        modelMap.addAttribute("changeType", "checkbox");
        modelMap.addAttribute("baiduUrl", "/test/log");
        modelMap.addAttribute("city", cities.get(0));
        modelMap.addAttribute("shopLogo",
                "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
        modelMap.addAttribute("shopLogo",
                "/upload/1111.png");
        modelMap.addAttribute("country", country);
        modelMap.addAttribute("cities", cities);
        modelMap.addAttribute("updateCityUri", "/api/city");
        modelMap.addAttribute("template", "test/index");
        // 返回外层的碎片组装器
        return "index";
    }

}
