package com.wpg.graduationdesign.moudles.shones.entity;

import java.io.Serializable;

/**
 * (Category)实体类
 *
 * @author makejava
 * @since 2020-09-16 16:44:02
 */
public class Category implements Serializable {
    private static final long serialVersionUID = 408525474255997500L;
    
    private Integer categoryId;
    /**
    * 商品种类
    */
    private String name;


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}