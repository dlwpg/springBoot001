package com.wpg.graduationdesign.moudles.shones.entity;

import java.io.Serializable;

/**
 * (receiveAddress)实体类
 *
 * @author wpg
 * @since 2020-09-16 16:44:50
 */
public class ReceiveAddress implements Serializable {
    private static final long serialVersionUID = 7758889277779767990L;
    private Integer addressId;
    private Integer userId;
    private String userName;
    private String userPhone;
    private String address;
    private Integer status;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "ReceiveAddress{" +
                "addressId=" + addressId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}