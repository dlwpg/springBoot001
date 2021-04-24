package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.Role;
import com.wpg.graduationdesign.moudles.shones.entity.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface USerRoleDao {

    //通过uid获取用户拥有的权限
    @Select("select * from user_role where u_id=#{userId}")
    @Results(value = {
            //使用了userid接受roles后本身被占用，二次映射本身
            @Result(column = "r_id",property = "roleId"),
            @Result(column = "u_id",property = "userId"),
            //用r_id字段做条件查询，实体类user里有roles属性
            @Result(column = "r_id",property = "roles",
                    javaType = List.class,
                    //cites映射的包名.类名.方法名
                    many = @Many(select = "com.wpg.graduationdesign.moudles.shones.dao.RoleDao.getRolesByUserId"))
    })
    List<UserRole> getRolesByUserId(int userId);

    //通过用户id删除权限
    @Delete("delete from user_role where u_id=#{userId}")
    void deleteAllUserRoleByUserId(int userId);

    //插入权限
    @Insert("insert into user_role (u_id,r_id) values(#{userId},#{roleId})")
//    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertRole(@RequestParam("userId") int userId,@RequestParam("roleId") int roleId);
}
