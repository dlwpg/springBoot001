package com.wpg.graduationdesign.moudles.shones.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Role;
import com.wpg.graduationdesign.moudles.shones.entity.User;
import com.wpg.graduationdesign.moudles.shones.service.UserService;
import com.wpg.graduationdesign.utils.MD5Util;
import com.wpg.graduationdesign.utils.SendEmail;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;

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
    //  xxx?method=xxx --->@RequsetParam("method") String method
    //  xxx  传入对象  @RequestBody User user
    @PostMapping(value = "/login", consumes = "application/json")
    public Result<User> loginByUser(@RequestBody User user) {
        return userService.loginByUser(user);
    }

    /**
     * 127.0.0.1/shone/register   ---post
     * {"userName":"wpg","password":"123456","sex":"","email":"2389038599@qq.com"}
     */
    @PostMapping(value = "/register", consumes = "application/json")
    public Result<User> registerByUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    /**
     * 127.0.0.1/shone/userImg ---- post
     */
    @PostMapping(value = "/userImg", consumes = "multipart/form-data")
    //文件名称一定要对称，和form中name的对应
    @ResponseBody
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file,
                                     @RequestParam("userId") String userId) {
        return userService.uploadUserImg(file, userId);
    }

    @PostMapping("/user/{userId}")
    public Result<User> getImageByUserId(@PathVariable int userId) {
        return userService.getImageByUserId(userId);
    }

    /**
     * 个人修改个人信息
     * 127.0.0.1/shone/user ---- put
     */
    @PutMapping(value = "/user", consumes = "application/json")
    public Result<User> updateProfile(@RequestBody User user) {
//        System.out.println(userService.updateProfile(user));
        return userService.updateProfile(user);
    }

    /**
     * 127.0.0.1/shone/md5password?oldPassword=xxx, ---- post
     */
    @PostMapping(value = "/md5password")
    public String getMd5Password(@RequestParam("oldPassword") String oldPassword) {
        return MD5Util.getMD5(oldPassword);
    }

    /**
     * 127.0.0.1/shone/password ---- put
     */
    @PutMapping(value = "/password", consumes = "application/json")
    public String updatePassword(@RequestBody User user) {
        try {
            String content = "<a href='http://127.0.0.1/nxzm/user/revisepassword?userId="
                    + user.getUserId() + "&newPassword=" + user.getPassWord() + "'>请点击确认修改</a>";
            SendEmail.sendMail(user.getEmail(), "这是一封确认邮件", content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分页查询
     * 127.0.0.1/shone/users   ---post
     * {"currentPage":"1","pageSize":"5","keyword":"","orderBy":"","sort":""}
     */
    @PostMapping(value = "/users", produces = "application/json", consumes = "application/json")
    public PageInfo<User> getUsers(@RequestBody SearchVo searchVo) {
        return userService.getUsersBySearchVo(searchVo);
    }


    /**
     * 获取所有权限
     * 127.0.0.1/shone/roles   ---post
     */
    @PostMapping(value = "/roles", produces = "application/json")
    public List<Role> getRoles() {
        return userService.getRoles();
    }


    /**
     * 通过用户id查询所有信息包括权限
     * 127.0.0.1/shone/roles   ---post
     */
    @PostMapping(value = "/roles/{userId}", produces = "application/json")
    public Result<User> getUserAndRoles(@PathVariable int userId) {
        return userService.getUserAndRoles(userId);
    }

    /**
     * 管理员修改
     * 127.0.0.1/shone/a_user   ---put
     * {xxx}
     */
    @PutMapping(value = "/a_user", consumes = "application/json")
    public Result<User> updateUser(@RequestBody User user) {
        Result<User> result = userService.updateProfile(user);
        int status = result.getStatus();
//        System.out.println(user.getUserRoles().toString());
        if (status == 200) {
            return userService.updateRole(user.getUserId(), user.getUserRoles());
        } else {
            return result;
        }

    }

    /**
     * @return 127.0.0.1/shone/password/1   ---put
     */
    @PutMapping("/password/{userId}")
    public Result<String> resetPasswrod(@PathVariable int userId) {
        return userService.resetPassword(userId);
    }


    /**
     * 删除用户
     *
     * @return
     */
    @DeleteMapping("/user/{userId}")
    public Result<String> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }
}
