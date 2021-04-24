package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.User;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserDao {
    //登录
    @Select("select * from user where username=#{userName}")
    User loginByUser(String userName);

    //通过id查找
    @Select("select * from user where userid=#{userId}")
    User selectUserByUserId(int userId);

    //注册
    @Insert("insert into user (username,password,sex,email,status) values(#{userName},#{passWord},#{sex},#{email},#{status})")
    @Options(useGeneratedKeys = true, keyColumn = "userid", keyProperty = "userId")
    //必须传对象才能回填
    int registerUser(User user);

    //激活
    @Update("update  user set status=1 where userid = #{userId}")
    void activeStatus(int userId);

    //修改图片路径
    @Update("update  user set image=#{destpath} where userid = #{userId}")
    void updateImg(@RequestParam("destpath") String destpath,@RequestParam("userId") String userId);

    @Select("select * from user where userid=#{userId}")
    User getImageByUserId(@RequestParam("userId") int userId);

    //修改其他信息
    @Update("update  user set receiveaddress=#{receiveAddress},username=#{userName},phone=#{phone},email=#{email} where userid = #{userId}")
    void updateProfile(User user);

    //修改密码
    @Update("update  user set password=#{password} where userid = #{userId}")
    void revisePassword(@RequestParam("userId") int userId,@RequestParam("password") String password);

    @Select("<script>" +
            "select * from user "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (username like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " order by userid desc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<User> getusersBySearchVo(SearchVo searchVo);


    //通过用户Id获取用户所有信息和权限
    @Select("select * from user where userid=#{userId}")
    //使用了id 下面用@ResultMap(value = "country_cities")就可以访问
    @Results(id = "user_roles",value = {
            //使用了userid接受roles后本身被占用，二次映射本身
            @Result(column = "userid",property = "userId"),
            //用userid字段作为条件查询，实体类user里有roles属性
            @Result(column = "userid",property = "userRoles",
                    javaType = List.class,
                    //映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.graduationdesign.moudles.shones.dao.USerRoleDao.getRolesByUserId"))
    })
    User getUserAndRoles(int userId);

    //重置密码
    @Update("update user set password=#{password} where userid=#{userId}")
    int resetPassword(@RequestParam("userId") int userId,@RequestParam("password") String password);

    @Delete("delete from user where userid=#{userId}")
    void deleteUser(int userId);

    //修改前台其他信息
    @Update("update  user set receiveaddress=#{receiveAddress},username=#{userName},sex=#{sex},phone=#{phone} where userid = #{userId}")
    void updateProfileQt(User user);
}
