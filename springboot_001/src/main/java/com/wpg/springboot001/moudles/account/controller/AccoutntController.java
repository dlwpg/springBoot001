package com.wpg.springboot001.moudles.account.controller;

import com.wpg.springboot001.moudles.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
public class AccoutntController {
    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1/account/login
     */
    @GetMapping("/login")
    public String login(){
        return "indexsample";
    }

    /**
     * 127.0.0.1/account/logout   ---post
     */
    @GetMapping(value = "/logout")
    public String logout(ModelMap modelMap) {
        userService.logout();
        modelMap.addAttribute("template","account/login");
        return "indexsample";
    }

    /**
     * 127.0.0.1/account/register ---- get
     */
    @GetMapping("/register")
    public String registerPage() {
        return "indexsample";
    }


    /**
     * @return 192.168.18.232/account/users---get
     */
    @GetMapping("/users")
    public String users() {
        return "index";
    }

    /**
     * @return 192.168.18.232/account/roles---get
     */
    @GetMapping("/roles")
    public String roles() {
        return "index";
    }

    /**
     * @return 192.168.18.232/account/resources---get
     */
    @GetMapping("/resources")
    public String resources() {
        return "index";
    }

    /**
     * @return 192.168.18.232/account/profile---get
     */
    @GetMapping("/profile")
    public String profilePage() {
        return "index";
    }

    @GetMapping("/403")
    public String errorpageFor403() {
        return "index";
    }
}
