package com.wpg.graduationdesign.moudles.shones.entity;

import java.io.Serializable;

/**
 * (ShoppingCar)实体类
 *
 * @author makejava
 * @since 2020-09-16 16:44:50
 */
public class ShoppingCar implements Serializable {

    private static final long serialVersionUID = -5475351424488349693L;
    private Integer id;
    private Integer uId;
    private Integer pId;
    private String pName;
    private Integer num;
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "id=" + id +
                ", uId=" + uId +
                ", pId=" + pId +
                ", pName='" + pName + '\'' +
                ", num=" + num +
                '}';
    }
}