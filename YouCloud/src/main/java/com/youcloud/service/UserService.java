package com.youcloud.service;

import com.youcloud.pojo.User;


import java.util.List;

/**
 * @author so1esou1
 * @ClassName   用户的serice层接口
 * @Date 2021.1.4
 * @TODO
 */

public interface UserService {
    /**
        @ClassName UserService.java
        @author so1esou1
        @Date 2021.1.4  
        @TODO 增加用户，返回成功与否
    */    
    boolean addUser(User user);
    
    /**
        @ClassName UserService.java
        @author so1esou1
        @Date 2021.1.4  
        @TODO 删除用户，返回是否删除成功
    */    
    boolean deleteByID(Integer userID);
    
    /**
        @ClassName UserService.java
        @author so1esou1
        @Date 2021.1.4  
        @TODO 根据昵称删除用户,返回是否删除成功
    */    
    boolean deleteByNick(String userNick);
    
    /**
        @ClassName UserService.java
        @author so1esou1
        @Date 2021.1.4  
        @TODO 根据id查询用户,返回一个用户
    */
    User queryByID(Integer userID);
    
    /**
        @ClassName UserService.java
        @author so1esou1
        @Date 2021.1.4  
        @TODO 根据昵称查询用户信息,返回一个用户
    */    
    User queryByNick(String userNick);

    /**
        @ClassName UserService.java
        @author so1esou1
        @Date 2021.1.4  
        @TODO 查询全部用户数据，返回用户的集合
    */    
    List<User> queryAll();

    /**
        @ClassName UserService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过用户id修改用户信息,返回修改成功与否
    */
    boolean updateUser(User user);

    /**
        @ClassName UserService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过用户id获取其邮箱
    */
    String queryEmailById(Integer userID);


    /**
     * 根据邮箱查找用户
     * @param userEmail
     * @return
     */
    User getUserByEmail(String userEmail);


    /**
     * 修改用户信息
     * userNick   userPassword    userEmail   userAddr    userContact
     * @return
     */
    boolean changeUser(Integer userID,
                       String userNick,
                       String userPassword,
                       String userEmail,
                       String userAddr,
                       String userContact);
}
