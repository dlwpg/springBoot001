package com.wpg.springboot001.moudles.account.dao;

import com.wpg.springboot001.moudles.account.pojo.Role;
import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Mapper
public interface RoleDao {
    @Select("select * from role r left join user_role ur on r.role_id=ur.role_id where ur.user_id=#{userId}")
    List<Role> selectRolesByUserId(int userId);

    @Select("select * from role")
    List<Role> selectRoles();


    @Select("<script>" +
            "select * from role "
            + "<where>"
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (role_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test= 'orderBy != \"\" and orderBy != null' >"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise> "
            + " order by role_id asc "
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Role> getRolesBySearchVo(SearchVo searchVo);

    //通过rolename查询
    @Select("select * from role where role_name=#{roleName}")
    Role selectRoleByRoleName(String roleName);

    //增加角色
    @Insert("insert into role (role_name) values(#{roleName})")
    @Options(useGeneratedKeys = true, keyColumn = "role_id", keyProperty = "roleId")
    int insertRoleByRole(Role role);

    //删除角色
    @Delete("delete from role where role_id=#{roleId} ")
    void deleteRole(int roleId);

    //修改角色
    @Update("update role set role_name=#{roleName} where role_id=#{roleId}")
    int updateRole(Role role);

    //通过roleid查询
    @Select("select * from role where role_id=#{roleId}")
    Role selectRoleByRoleId(int roleId);


}
