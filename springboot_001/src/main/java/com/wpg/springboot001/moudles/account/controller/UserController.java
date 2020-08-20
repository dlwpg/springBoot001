package com.wpg.springboot001.moudles.account.controller;

import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.moudles.account.service.UserService;
import com.wpg.springboot001.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 127.0.0.1/api/user   ---post
     * {"userName":"wpg","password":"123456"}
     */
    @PostMapping(value = "/user",consumes = "application/json")
    public Result<User> insertUserByUser(@RequestBody User user){
        return userService.insertUserByUser(user);
    }


    /**
     * 127.0.0.1/api/login   ---post
     */
    @PostMapping(value = "/login",consumes = "application/json")
    public Result<User> login(@RequestBody User user){
        return userService.selectUserByUserName(user);
    }
}
