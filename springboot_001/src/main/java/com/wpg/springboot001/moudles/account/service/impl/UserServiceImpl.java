package com.wpg.springboot001.moudles.account.service.impl;

import com.wpg.springboot001.moudles.account.dao.UserDao;
import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.moudles.account.service.UserService;
import com.wpg.springboot001.moudles.utils.MD5Util;
import com.wpg.springboot001.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result<User> insertUserByUser(User user) {
        if (userDao.selectUserByUserName(user.getUserName()) != null) {
            return new Result<User>(Result.Resultstatus.FAILD.status,
                    "username is alive");
        }
        user.setCreateDate(LocalDateTime.now());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userDao.insertUserByUser(user);
        return new Result<User>(Result.Resultstatus.SUCCESS.status,
                "insert user success!", user);
    }

    @Override
    public Result<User> selectUserByUserName(User user) {

        User user1 = userDao.selectUserByUserName(user.getUserName());
        if (user1 != null && user1.getPassword().equals(MD5Util.getMD5(user.getPassword()))) {
            return new Result<User>(Result.Resultstatus.SUCCESS.status, "login success");
        }
        return new Result<User>(Result.Resultstatus.FAILD.status,
                "username or password is error");
    }
}
