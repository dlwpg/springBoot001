package com.wpg.springboot001.moudles.account.dao;

import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    @Insert("insert into user (user_name,password,user_img,create_date) " +
            "values(#{userName},#{password},#{userImg},#{createDate})")
    @Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")
    int insertUserByUser(User user);

    @Select("select * from user where user_name=#{userName}")
    @ResultMap(value = "roles")
    User selectUserByUserName(String userName);


    @Select("<script>" +
            "select * from user "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (user_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " order by user_id desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<User> getusersBySearchVo(SearchVo searchVo);


    @Update("update user set user_name=#{userName},user_img=#{userImg} where user_id=#{userId}")
    int updateUser(User user);

    @Delete("delete from user where user_id=#{userId} ")
    void deleteUser(int userId);

    @Select("select * from user where user_id=#{userId}")
    @Results(id = "roles",value = {
            @Result(column = "user_id",property = "userId"),
            @Result(column = "user_id",property = "roles",
            javaType = List.class,
            many = @Many(select = "com.wpg.springboot001.moudles.account.dao.RoleDao.selectRolesByUserId"))
    })
    User selectByUserId(int userId);
}
