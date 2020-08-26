package com.wpg.springboot001.moudles.account.service;

import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.moudles.account.pojo.Role;
import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.vo.Result;
import com.wpg.springboot001.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    Result<User> insertUserByUser(User user);

    Result<User> selectUserByUserName(User user);
    //通过userid 查询结果
    User selectByUserId(int userId);

    //分页查询
    PageInfo<User> getUsersBySearchVo(SearchVo searchVo);
    //修改
    Result<User> updateUser(User user);
    //删除
    Result<Object> deleteUser(Integer userId);

    //查询所有的role对象
    List<Role> selectRoles();

    Result<String> uploadUserImg(MultipartFile file);

    Result<User> updateUserProfile(User user);

    void logout();
}
