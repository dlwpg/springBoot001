package com.wpg.graduationdesign.moudles.shones.controller;

import com.wpg.graduationdesign.moudles.shones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nxzm")
public class AccoutntController {

    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1/nxzm/login   ==get
     */
    @GetMapping("/login")
    public String login(){
        return "indexsample";
    }

    /**
     * 127.0.0.1/nxzm/index   ==get
     */
    @GetMapping("/mainpage")
    public String index(){
        return "index";
    }

    /**
     * 127.0.0.1/nxzm/register   ==get
     */
    @GetMapping("/register")
    public String register(){
        return "indexsample";
    }

    /**
     * 127.0.0.1/nxzm/register   ==get
     */
    @GetMapping("/activation/{userId}")
    @ResponseBody
    public String activestatus(@PathVariable int userId){
        userService.activeStatus(userId);
        return "激活成功！";
    }
}
