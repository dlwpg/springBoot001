package com.wpg.springboot001.moudles.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.moudles.account.dao.RoleDao;
import com.wpg.springboot001.moudles.account.pojo.Role;
import com.wpg.springboot001.moudles.account.pojo.User;
import com.wpg.springboot001.moudles.account.service.RoleService;
import com.wpg.springboot001.vo.Result;
import com.wpg.springboot001.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public PageInfo<Role> getRolesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<Role>(Optional
                .ofNullable(roleDao.getRolesBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<Role> insertRoleByRole(Role role) {
        Role roletemp = roleDao.selectRoleByRoleName(role.getRoleName());
        if (roletemp != null) {
            return new Result<Role>(Result.Resultstatus.FAILD.status, "this role is repeat.");
        }
        roleDao.insertRoleByRole(role);
        return new Result<Role>(Result.Resultstatus.SUCCESS.status
                , "insert role is success.", role);
    }

    @Override
    @Transactional
    public Result<Role> deleteRole(int roleId) {
        roleDao.deleteRole(roleId);
        return new Result<Role>(Result.Resultstatus.SUCCESS.status
                , "delete role is success.");
    }

    @Override
    @Transactional
    public Result<Role> updateRole(Role role) {
        Role roletemp = roleDao.selectRoleByRoleName(role.getRoleName());
        if (roletemp != null && role.getRoleId() != roletemp.getRoleId()) {
            return new Result<Role>(Result.Resultstatus.FAILD.status, "this role is repeat.");
        }
        roleDao.updateRole(role);
        return new Result<Role>(Result.Resultstatus.SUCCESS.status
                , "update role is success.", role);
    }

    @Override
    @Transactional
    public Role selectRoleByRoleId(int roleId) {
        return roleDao.selectRoleByRoleId(roleId);
    }
}
