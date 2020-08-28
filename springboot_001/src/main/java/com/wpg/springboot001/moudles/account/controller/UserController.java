package com.wpg.springboot001.moudles.account.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.moudles.account.pojo.Role;
import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.moudles.account.service.UserService;
import com.wpg.springboot001.vo.Result;
import com.wpg.springboot001.vo.SearchVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;



    /**
     * 127.0.0.1/api/login   ---post
     */
    @PostMapping(value = "/login", consumes = "application/json")
    public Result<User> login(@RequestBody User user) {
        return userService.selectUserByUserName(user);
    }



    /**
     * 127.0.0.1/api/user/3 ---get
     */
    @GetMapping("/user/{userId}")
    public User selectByUserId(@PathVariable int userId) {
        return userService.selectByUserId(userId);
    }

    /**
     * 增加
     * 127.0.0.1/api/user   ---post
     * {"userName":"wpg","password":"123456"}
     */
    @PostMapping(value = "/user", consumes = "application/json")
    public Result<User> insertUserByUser(@RequestBody User user) {
        return userService.insertUserByUser(user);
    }


    /**
     * 分页查询
     * 127.0.0.1/api/users   ---post
     * {"currentPage":"1","pageSize":"5","keyword":"","orderBy":"","sort":""}
     */
    @PostMapping(value = "/users", produces = "application/json", consumes = "application/json")
    public PageInfo<User> getUsers(@RequestBody SearchVo searchVo) {
        return userService.getUsersBySearchVo(searchVo);
    }


    /**
     * 修改
     * 127.0.0.1/api/user   ---put
     * {"userName":"wpg","userImg":"xxx.jpg"}
     */
    @PutMapping(value = "/user", consumes = "application/json")
    public Result<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    /**
     * 删除
     * 127.0.0.1/api/user/1   ---delete
     */
    @DeleteMapping(value = "/user/{userId}")
    @RequiresPermissions(value = "/api/user")
    public Result<Object> updateUser(@PathVariable Integer userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return userService.selectRoles();
    }


    /**
     * 127.0.0.1/api/userImg ---- post
     */
    @PostMapping(value = "/userImg", consumes = "multipart/form-data")
    public Result<String> uploadFile(@RequestParam MultipartFile file) {
        return userService.uploadUserImg(file);
    }

    /**
     * 127.0.0.1/api/profile ---- put
     */
    @PutMapping(value = "/profile", consumes = "application/json")
    public Result<User> updateUserProfile(@RequestBody User user) {
        return userService.updateUserProfile(user);
    }
}
