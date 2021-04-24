package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Role;
import com.wpg.graduationdesign.moudles.shones.entity.User;
import com.wpg.graduationdesign.moudles.shones.entity.UserRole;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    Result<User> loginByUser(User user);

    Result<User> registerUser(User user);

    //激活
    void activeStatus(int userId);

    //修改头像
    Result<String> uploadUserImg(MultipartFile file, String userId);


    Result<User> getImageByUserId(int userId);

    //修改其他信息
    Result<User> updateProfile(User user);

    //修改密码
    Result<String> revisePassword(int userId, String newPassword);

    //分页查询
    PageInfo<User> getUsersBySearchVo(SearchVo searchVo);

    //获取所有权限
    List<Role> getRoles();

    //通过用户id获取用户信息和拥有的权限
    Result<User> getUserAndRoles(int userId);

    //重置密码
    Result<String > resetPassword(int userId);

    //删除用户
    Result<String> deleteUser(int userId);

    //修改权限
    Result<User> updateRole(int userId,List<UserRole> userRole);

    //匹配前台原密码
    Result<User> checkOldPassword(User user);

    //修改前台其他信息
    Result<User> updateSetting(User user);
}
