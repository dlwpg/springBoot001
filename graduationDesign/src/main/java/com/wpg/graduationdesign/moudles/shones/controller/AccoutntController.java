package com.wpg.graduationdesign.moudles.shones.controller;

import com.wpg.graduationdesign.moudles.shones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/nxzm")
public class AccoutntController {

    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1/nxzm/user/login   ==get
     */
    @GetMapping("/user/login")
    public String login() {
        return "indexsample";
    }

    /**
     * 127.0.0.1/nxzm/user/mainpage   ==get
     */
    @GetMapping("/user/mainpage")
    public String index() {
        return "index";
    }

    /**
     * 127.0.0.1/nxzm/user/register   ==get
     */
    @GetMapping("/user/register")
    public String register() {
        return "indexsample";
    }

    /**
     * 127.0.0.1/nxzm/user/register   ==get
     */
    @GetMapping("/user/activation/{userId}")
    @ResponseBody
    public String activestatus(@PathVariable int userId) {
        userService.activeStatus(userId);
        return "激活成功！";
    }


    /**
     * 127.0.0.1/nxzm/user/profile   ==get
     */
    @GetMapping("/user/profile")
    public String profile() {
        return "index";
    }

    /**
     * 127.0.0.1/nxzm/user/edituserpassword   ==get
     */
    @GetMapping("/user/edituserpassword")
    public String editUserPassword() {
        return "index";
    }

    /**
     * 127.0.0.1/nxzm/user/revisepassword   ==get
     */
    @GetMapping("/user/revisepassword")
    @ResponseBody
    public String revisePassword(@RequestParam("userId") int userId,
                                 @RequestParam("newPassword") String newPassword) {
        userService.revisePassword(userId, newPassword);
        return "密码修改成功！";
    }

//    /**
//     * 127.0.0.1/nxzm/user/users   ==get
//     */
//    @GetMapping("/user/users")
//    public String users(HttpServletRequest request) {
//        String template = (String) request.getAttribute("template");
//        return "index::"+template;
//    }


    /**
     * http://127.0.0.1/nxzm/user/users   ==get
     */
    @GetMapping("/user/users")
    public String users() {
        return "index";
    }

    /**
     * 127.0.0.1/nxzm/role/roles   ==get
     */
    @GetMapping("/role/roles")
    public String roles() {
        return "index";
    }

}
