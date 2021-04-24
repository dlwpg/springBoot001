package com.wpg.graduationdesign.moudles.shones.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (UserRole)实体类
 *
 * @author makejava
 * @since 2020-09-14 16:44:51
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = -20681763824129802L;
    
    private Integer id;
    
    private Integer userId;
    
    private Integer roleId;

    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", roles=" + roles +
                '}';
    }
}