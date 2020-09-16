package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.Role;
import com.wpg.graduationdesign.moudles.shones.entity.User;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleDao {

    //获取所有权限
    @Select("select * from role")
    List<Role> getRoles();

    //通过rid获取用户拥有的权限
    @Select("select * from role where role_id=#{roleId}")
    Role getRolesByUserId(int roleId);


    @Select("<script>" +
            "select * from role "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (name like '%${keyWord}%') "
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
    List<Role> getRolesBySearchVo(SearchVo searchVo);

    //插入权限
    @Insert("insert into role (name) values(#{name}) ")
    @Options(useGeneratedKeys = true, keyColumn = "role_id", keyProperty = "roleId")
    int  insertByRole(Role role);

    //修改权限
    @Update("update role set name=#{name} where role_id=#{roleId}")
    int updateRole(Role role);

    //删除
    @Delete("delete from role where role_id=#{roleId}")
    int deleteRoleByroleId(int roleId);
}
