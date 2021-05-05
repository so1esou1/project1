package com.youcloud.pojo;

import java.io.Serializable;
import java.util.List;

/**
    @ClassName 用户实体类
    @author so1esou1
    @Date 2021.1.4
    @TODO
*/

public class User implements Serializable {
    /**
        用户的id，也是账号，唯一标识
     */
    private Integer userID;
    /**
        用户的昵称，未修改的话是id
     */
    private String userNick;
    /**
        用户的密码
     */
    private String userPassword;
    /**
        用户的邮箱
     */
    private String userEmail;
    /**
        用户的地址
     */
    private String userAddr;
    /**
        用户的联系方式
     */
    private String userContact;


    /**
     * 用户的头像，存储的是地址
     */
    private String userProfile;


    /**
     * 用户的身份:普通用户、普通会员和大会员
     */
    private String userRole;


    /**
     *  用户关联多个朋友圈
     */
    private List<Circle> circles;


    public User() {
    }

    public User(Integer userID, String userNick, String userPassword, String userEmail, String userAddr, String userContact, String userProfile, String userRole) {
        this.userID = userID;
        this.userNick = userNick;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddr = userAddr;
        this.userContact = userContact;
        this.userProfile = userProfile;
        this.userRole = userRole;
    }


    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userNick='" + userNick + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAddr='" + userAddr + '\'' +
                ", userContact='" + userContact + '\'' +
                ", userProfile='" + userProfile + '\'' +
                ", userRole='" + userRole + '\'' +
                ", circles=" + circles +
                '}';
    }
}
