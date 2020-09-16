package com.wpg.graduationdesign.moudles.shones.service;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Role;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;

public interface RoleService {
    //获取所有roles信息
    PageInfo<Role> getRolesBySearchVo(SearchVo searchVo);

    //插入权限
    Result<Role> insertByRole(Role role);

    //查询权限
    Result<Role> selectByRoleId(int roleId);

    //修改
    Result<Role> updateRole(Role role);

    Result<Role> deleteRoleByroleId(int roleId);
}
