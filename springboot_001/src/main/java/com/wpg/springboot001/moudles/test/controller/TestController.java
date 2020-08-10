package com.wpg.springboot001.moudles.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class TestController {
    /**
     * @return 192.168.18.232:8080/test/test001
     */
    @GetMapping("/test001")
    @ResponseBody
    public String test001(){
        return "this is test 001.";
    }

}
