package com.wpg.springboot001.moudles.account.dao;

import com.wpg.springboot001.moudles.account.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    @Insert("insert into user (user_name,password,user_img,create_date) " +
            "values(#{userName},#{password},#{userImg},#{createDate})")
    @Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")
    int insertUserByUser(User user);

    @Select("select * from user where user_name=#{userName}")
    User selectUserByUserName(String userName);
}
