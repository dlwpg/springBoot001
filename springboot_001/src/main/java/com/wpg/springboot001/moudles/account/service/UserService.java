package com.wpg.springboot001.moudles.account.service;

import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.vo.Result;

public interface UserService {
    Result<User> insertUserByUser(User user);

    Result<User> selectUserByUserName(User user);
}
