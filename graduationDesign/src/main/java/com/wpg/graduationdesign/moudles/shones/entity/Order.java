package com.wpg.graduationdesign.moudles.shones.entity;

import java.io.Serializable;

/**
 * (Order)实体类
 *
 * @author makejava
 * @since 2020-09-19 09:12:37
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -71697304743577519L;
    /**
    * 订单id
    */
    private Integer orderId;

    private Integer userId;
    
    private Integer orderProductId;
    /**
    * 商品数目:买了几个商品
    */
    private Integer orderProductNumbers;
    /**
    * 订单编号
    */
    private String orderNumber;
    /**
    * 产品名称
    */
    private String orderProductName;
    /**
    * 此订单成交额
    */
    private Double orderProductTotalPrice;
    /**
    * 快递公司
    */
    private String courierServicesCompany;
    /**
    * 收货地址
    */
    private String orderAddress;
    /**
    * 买家的用户名
    */
    private String orderUserName;
    /**
    * 交易时间
    */
    private String orderTradingTimeDetail;
    
    private String orderProductImage;
    /**
    * 商品类型
    */
    private Integer orderProductType;
    /**
    * 产品单价
    */
    private Double orderProductPrice;
    /**
    * 订单的支付情况：0-->未支付  1-->已支付
    */
    private String orderPaymentStatus;
    /**
    * 0-->未发货  1-->已发货
    */
    private Integer orderSendStatus;
    /**
    * 0 -->未收货  1-->已收货
    */
    private Integer orderConfirmStatus;
    /**
    * 0 -->未评论 1-->已评论
    */
    private Integer orderCommentStatus;
    /**
    * 订单所属省份
    */
    private String orderProvince;
    
    private String orderTradingTimeYear;
    /**
    * 0--->未退款 1--->已退款
    */
    private Integer orderStatus;
    /**
    * 0-->未发起退款请求  1-->发起退款请求
    */
    private Integer orderBackMoneyStatus;

    //电话
    private String userPhone;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Integer orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Integer getOrderProductNumbers() {
        return orderProductNumbers;
    }

    public void setOrderProductNumbers(Integer orderProductNumbers) {
        this.orderProductNumbers = orderProductNumbers;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderProductName() {
        return orderProductName;
    }

    public void setOrderProductName(String orderProductName) {
        this.orderProductName = orderProductName;
    }

    public Double getOrderProductTotalPrice() {
        return orderProductTotalPrice;
    }

    public void setOrderProductTotalPrice(Double orderProductTotalPrice) {
        this.orderProductTotalPrice = orderProductTotalPrice;
    }

    public String getCourierServicesCompany() {
        return courierServicesCompany;
    }

    public void setCourierServicesCompany(String courierServicesCompany) {
        this.courierServicesCompany = courierServicesCompany;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public String getOrderTradingTimeDetail() {
        return orderTradingTimeDetail;
    }

    public void setOrderTradingTimeDetail(String orderTradingTimeDetail) {
        this.orderTradingTimeDetail = orderTradingTimeDetail;
    }

    public String getOrderProductImage() {
        return orderProductImage;
    }

    public void setOrderProductImage(String orderProductImage) {
        this.orderProductImage = orderProductImage;
    }

    public Integer getOrderProductType() {
        return orderProductType;
    }

    public void setOrderProductType(Integer orderProductType) {
        this.orderProductType = orderProductType;
    }

    public Double getOrderProductPrice() {
        return orderProductPrice;
    }

    public void setOrderProductPrice(Double orderProductPrice) {
        this.orderProductPrice = orderProductPrice;
    }

    public String getOrderPaymentStatus() {
        return orderPaymentStatus;
    }

    public void setOrderPaymentStatus(String orderPaymentStatus) {
        this.orderPaymentStatus = orderPaymentStatus;
    }

    public Integer getOrderSendStatus() {
        return orderSendStatus;
    }

    public void setOrderSendStatus(Integer orderSendStatus) {
        this.orderSendStatus = orderSendStatus;
    }

    public Integer getOrderConfirmStatus() {
        return orderConfirmStatus;
    }

    public void setOrderConfirmStatus(Integer orderConfirmStatus) {
        this.orderConfirmStatus = orderConfirmStatus;
    }

    public Integer getOrderCommentStatus() {
        return orderCommentStatus;
    }

    public void setOrderCommentStatus(Integer orderCommentStatus) {
        this.orderCommentStatus = orderCommentStatus;
    }

    public String getOrderProvince() {
        return orderProvince;
    }

    public void setOrderProvince(String orderProvince) {
        this.orderProvince = orderProvince;
    }

    public String getOrderTradingTimeYear() {
        return orderTradingTimeYear;
    }

    public void setOrderTradingTimeYear(String orderTradingTimeYear) {
        this.orderTradingTimeYear = orderTradingTimeYear;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderBackMoneyStatus() {
        return orderBackMoneyStatus;
    }

    public void setOrderBackMoneyStatus(Integer orderBackMoneyStatus) {
        this.orderBackMoneyStatus = orderBackMoneyStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderProductId=" + orderProductId +
                ", orderProductNumbers=" + orderProductNumbers +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderProductName='" + orderProductName + '\'' +
                ", orderProductTotalPrice=" + orderProductTotalPrice +
                ", courierServicesCompany='" + courierServicesCompany + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderUserName='" + orderUserName + '\'' +
                ", orderTradingTimeDetail='" + orderTradingTimeDetail + '\'' +
                ", orderProductImage='" + orderProductImage + '\'' +
                ", orderProductType=" + orderProductType +
                ", orderProductPrice=" + orderProductPrice +
                ", orderPaymentStatus='" + orderPaymentStatus + '\'' +
                ", orderSendStatus=" + orderSendStatus +
                ", orderConfirmStatus=" + orderConfirmStatus +
                ", orderCommentStatus=" + orderCommentStatus +
                ", orderProvince='" + orderProvince + '\'' +
                ", orderTradingTimeYear='" + orderTradingTimeYear + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderBackMoneyStatus=" + orderBackMoneyStatus +
                ", userPhone='" + userPhone + '\'' +
                ", category=" + category +
                '}';
    }
}