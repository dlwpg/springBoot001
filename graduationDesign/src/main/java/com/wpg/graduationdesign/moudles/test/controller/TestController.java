package com.wpg.graduationdesign.moudles.test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/shonesluck")
public class TestController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/test")
    public String getTest(){
        return port;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
