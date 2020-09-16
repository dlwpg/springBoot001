package com.wpg.graduationdesign.moudles.shones.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.graduationdesign.moudles.shones.entity.Role;
import com.wpg.graduationdesign.moudles.shones.entity.User;
import com.wpg.graduationdesign.moudles.shones.service.RoleService;
import com.wpg.graduationdesign.vo.Result;
import com.wpg.graduationdesign.vo.SearchVo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shone")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 分页查询
     * 127.0.0.1/shone/roles   ---post
     * {"currentPage":"1","pageSize":"5","keyword":"","orderBy":"","sort":""}
     */
    @PostMapping(value = "/roles",produces = "application/json", consumes = "application/json")
    public PageInfo<Role> getRolesBySearchVo(@RequestBody SearchVo searchVo){
        return roleService.getRolesBySearchVo(searchVo);
    }


    /**
     * 添加
     * 127.0.0.1/shone/role   ---post
     */
    @PostMapping(value = "/role", consumes = "application/json")
    public Result<Role> insertByRole(@RequestBody Role role) {
        return roleService.insertByRole(role);
    }


    /**
     * 通过id查询权限
     * 127.0.0.1/shone/role/x   ---get
     */
    @GetMapping(value = "/role/{roleId}")
    public Result<Role> selectByRoleId(@PathVariable int roleId) {
        return roleService.selectByRoleId(roleId);
    }


    /**
     * 通过id修改权限
     * 127.0.0.1/shone/role   ---get
     */
    @PutMapping(value = "/role",consumes = "application/json")
    public Result<Role> updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    /**
     * 通过id删除权限
     * 127.0.0.1/shone/role/x   ---delete
     */
    @DeleteMapping(value = "/role/{roleId}")
    public Result<Role> deleteRoleByroleId(@PathVariable int roleId) {
        return roleService.deleteRoleByroleId(roleId);
    }


}
