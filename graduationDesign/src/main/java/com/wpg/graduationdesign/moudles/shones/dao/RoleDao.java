package com.wpg.graduationdesign.moudles.shones.dao;

import com.wpg.graduationdesign.moudles.shones.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
}
