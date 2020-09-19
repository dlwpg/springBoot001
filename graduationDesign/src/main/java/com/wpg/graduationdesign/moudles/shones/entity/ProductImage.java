package com.wpg.graduationdesign.moudles.shones.entity;

import java.io.Serializable;

/**
 * (ProductImage)实体类
 *
 * @author makejava
 * @since 2020-09-18 14:36:26
 */
public class ProductImage implements Serializable {
    private static final long serialVersionUID = -46446261753549336L;

    private Integer id;

    private Integer pid;

    private String pname;

    private String otherimage;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getOtherimage() {
        return otherimage;
    }

    public void setOtherimage(String otherimage) {
        this.otherimage = otherimage;
    }

}