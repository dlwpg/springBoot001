package com.wpg.graduationdesign.moudles.shones.entity;

import java.io.Serializable;

/**
 * (Review)实体类
 *
 * @author makejava
 * @since 2020-09-20 13:53:47
 */
public class Review implements Serializable {
    private static final long serialVersionUID = -27847888112912966L;
    
    private Integer reviewId;

    private String orderNumber;
    private Integer uid;
    
    private Integer pid;
    /**
    * 评价
    */
    private String content;
    
    private Integer contentLevel;
    
    private String createtime;

    //用户信息
    private User user;
    //商品信息
    private  Product product;
    //订单信息
    private  Order order;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentLevel() {
        return contentLevel;
    }

    public void setContentLevel(Integer contentLevel) {
        this.contentLevel = contentLevel;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

}