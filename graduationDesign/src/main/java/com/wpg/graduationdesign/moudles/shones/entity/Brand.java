package com.wpg.graduationdesign.moudles.shones.entity;

import java.io.Serializable;

/**
 * (Brand)实体类
 *
 * @author makejava
 * @since 2020-09-16 16:44:50
 */
public class Brand implements Serializable {
    private static final long serialVersionUID = 596495258479103296L;
    
    private Integer brandId;
    
    private String brandName;


    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                '}';
    }
}