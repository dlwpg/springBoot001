package com.wpg.graduationdesign.moudles.shones.service;

import com.wpg.graduationdesign.moudles.shones.pojo.User;
import com.wpg.graduationdesign.vo.Result;

public interface UserService {
    Result<User> loginByUser(User user);

    Result<User> registerUser(User user);

    //激活
    void activeStatus(int userId);
}
