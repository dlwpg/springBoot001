package com.wpg.graduationdesign.moudles.shones.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2020-09-16 16:00:07
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 110834648145703310L;

    private Integer productId;

    private String productName;

    private String description;
    /**
     * 原价
     */
    private Double originalPrice;
    /**
     * 现价
     */
    private Double promotePrice;
    /**
     * 库存
     */
    private Integer stock;

    private String mainImage;
    /**
     * 商品类型
     */
    private Integer productType;
    /**
     * 是否在售
     */
    private String productStatus;

    private Date createtime;
    /**
     * 品牌id
     */
    private Integer bId;

    //品牌
    private Brand brand;
    //种类
    private Category category;

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(Double promotePrice) {
        this.promotePrice = promotePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", originalPrice=" + originalPrice +
                ", promotePrice=" + promotePrice +
                ", stock=" + stock +
                ", mainImage='" + mainImage + '\'' +
                ", productType='" + productType + '\'' +
                ", productStatus='" + productStatus + '\'' +
                ", createtime=" + createtime +
                ", bId=" + bId +
                ", brand=" + brand +
                ", category=" + category +
                '}';
    }
}