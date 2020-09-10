package com.wpg.graduationdesign.moudles.shones.controller;

import com.wpg.graduationdesign.moudles.shones.pojo.User;
import com.wpg.graduationdesign.moudles.shones.service.UserService;
import com.wpg.graduationdesign.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shone")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1/shone/login   ---get
     * {"userName":"wpg","password":"123456"}
     */
    //  xxx/{x}--> @Pathvarible
    //  xxx?method=xxx --->@GetParam String method
    //  xxx  传入对象  @RequestBody User user
    @PostMapping(value = "/login",consumes = "application/json")
    public Result<User> loginByUser(@RequestBody User user){
        return userService.loginByUser(user);
    }

    /**
     * 127.0.0.1/shone/register   ---post
     * {"userName":"wpg","password":"123456","email":"2389038599@qq.com"}
     */
    @PostMapping(value = "/register",consumes = "application/json")
    public Result<User> registerByUser(@RequestBody User user){
        return userService.registerUser(user);
    }
}
