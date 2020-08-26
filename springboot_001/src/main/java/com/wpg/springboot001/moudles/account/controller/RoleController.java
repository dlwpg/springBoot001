package com.wpg.springboot001.moudles.account.controller;

import com.github.pagehelper.PageInfo;
import com.wpg.springboot001.moudles.account.pojo.Role;
import com.wpg.springboot001.moudles.account.service.RoleService;
import com.wpg.springboot001.vo.Result;
import com.wpg.springboot001.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private RoleService roleService;


    /**分页查询角色
     * 127.0.0.1/api/roles   ----post
     * {"currentPage":"1","pageSize":"2","keyword":"","orderBy":"","sort":""}
     */
    @PostMapping(value = "/roles",consumes = "application/json",produces ="application/json")
    public PageInfo<Role> getRolesBySearchVo(@RequestBody SearchVo searchVo){
        return roleService.getRolesBySearchVo(searchVo);
    }

    /**增加角色
     * 127.0.0.1/api/role   ----post
     * {"roleName":"dan"}
     */
    @PostMapping("/role")
    public Result<Role> insertRoleByRole(@RequestBody Role role){
        return roleService.insertRoleByRole(role);
    }

    /**删除角色
     * 127.0.0.1/api/role/4   ----delete
     */
    @DeleteMapping("/role/{roleId}")
    public Result<Role> deleteRole(@PathVariable int roleId){
        return roleService.deleteRole(roleId);
    }

    /**修改角色
     * 127.0.0.1/api/role   ----put
     * {"roleId":"3",roleName":"staff1"}
     */
    @PutMapping("/role")
    public Result<Role> updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    @GetMapping("/role/{roleId}")
    public Role selectRoleByRoleId(@PathVariable int roleId){
        return roleService.selectRoleByRoleId(roleId);
    }
}
