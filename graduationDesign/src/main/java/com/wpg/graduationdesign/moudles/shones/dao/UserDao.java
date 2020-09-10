package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    //登录
    @Select("select * from user where username=#{userName}")
    User loginByUser(String userName);

    //注册
    @Insert("insert into user (username,password,email,status) values(#{userName},#{passWord},#{email},#{status})")
    @Options(useGeneratedKeys = true, keyColumn = "userid", keyProperty = "userId")
    int registerUser(User user);

    //激活
    @Update("update  user set status=1 where userid = #{userId}")
    void activeStatus(int userId);
}
