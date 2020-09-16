package com.wpg.graduationdesign.moudles.shones.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.dao.RoleDao;
import com.wpg.graduationdesign.moudles.shones.entity.Role;
import com.wpg.graduationdesign.moudles.shones.service.RoleService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    @Transactional
    public PageInfo<Role> getRolesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<>(Optional.ofNullable(roleDao.getRolesBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<Role> insertByRole(Role role) {
        roleDao.insertByRole(role);
        return new Result<>(Result.Resultstatus.SUCCESS.status,"插入成功",role);
    }


    @Override
    @Transactional
    public Result<Role> selectByRoleId(int roleId) {
        Role role=roleDao.getRolesByUserId(roleId);
        return new Result<>(Result.Resultstatus.SUCCESS.status,"查询成功",role);
    }

    @Override
    @Transactional
    public Result<Role> updateRole(Role role) {
        roleDao.updateRole(role);
        return new Result<>(Result.Resultstatus.SUCCESS.status,"修改成功",role);
    }

    @Override
    @Transactional
    public Result<Role> deleteRoleByroleId(int roleId) {
        roleDao.deleteRoleByroleId(roleId);
        return new Result<>(Result.Resultstatus.SUCCESS.status,"删除成功");
    }
}
