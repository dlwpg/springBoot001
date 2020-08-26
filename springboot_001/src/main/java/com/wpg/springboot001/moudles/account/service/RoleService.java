package com.wpg.springboot001.moudles.account.service;

import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.moudles.account.pojo.Role;
import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.vo.Result;
import com.wpg.springboot001.vo.SearchVo;
import org.apache.ibatis.annotations.Delete;


public interface RoleService {
    //分页查询role
    PageInfo<Role> getRolesBySearchVo(SearchVo searchVo);
    //增加role
    Result<Role> insertRoleByRole(Role role);
    //删除role
    Result<Role> deleteRole(int roleId);
    //修改角色
    Result<Role> updateRole(Role role);

    //通过roleId查询角色
    Role selectRoleByRoleId(int roleId);

}
