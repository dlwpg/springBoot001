package com.wpg.graduationdesign.moudles.shones.entity;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-09-14 15:42:21
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 901936146550926829L;
    
    private Integer roleId;
    
    private String name;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }
}