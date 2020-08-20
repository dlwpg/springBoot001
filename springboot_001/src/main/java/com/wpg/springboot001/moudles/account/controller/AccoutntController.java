package com.wpg.springboot001.moudles.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccoutntController {

    /**
     * 127.0.0.1/account/login
     */
    @GetMapping("/login")
    public String login(){
        return "indexsample";
    }

    /**
     * 127.0.0.1/account/register ---- get
     */
    @GetMapping("/register")
    public String registerPage() {
        return "indexsample";
    }
}
