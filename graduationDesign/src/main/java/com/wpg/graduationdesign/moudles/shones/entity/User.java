package com.wpg.graduationdesign.moudles.shones.entity;

import java.io.Serializable;
import java.util.List;

//如果表名和类名不一样就加这个@Table(name="db_user")
public class User implements Serializable {
    private static final long serialVersionUID = 514622190976678602L;
    private int userId;
    private String userName;
    private String passWord;
    private String phone;
    private String receiveAddress;
    private String email;
    private String image;
    private int status;
    private String sex;


    private List<UserRole> userRoles;

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private boolean rememberMe;

    public boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phone='" + phone + '\'' +
                ", receiveAddress='" + receiveAddress + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", sex='" + sex + '\'' +
                ", userRoles=" + userRoles +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
