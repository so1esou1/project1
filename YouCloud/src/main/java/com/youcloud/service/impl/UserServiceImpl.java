package com.youcloud.service.impl;

import com.youcloud.pojo.User;
import com.youcloud.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author so1esou1
 * @ClassName 用户service接口实现类
 * @Date 2021.1.4
 * @TODO
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    /**
        @ClassName UserServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 增加用户，返回成功与否
    */
    public boolean addUser(User user) {
        if (userMapper.addUser(user) == 1){
            usersCirclesMapper.addUserUsersCircles(user.getUserID());
            return true;
        }
        return false;
    }

    /**
        @ClassName UserServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 删除用户，返回是否删除成功
    */
    public boolean deleteByID(Integer userID) {
        usersCirclesMapper.deleteUsersCirclesByUser(userID);
        if (userMapper.deleteByID(userID) == 1){

            return true;
        }
        return false;
    }

    /**
        @ClassName UserServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据昵称删除用户,返回是否删除成功
    */
    public boolean deleteByNick(String userNick) {
        User user = userMapper.queryByNick(userNick);
        usersCirclesMapper.deleteUsersCirclesByUser(user.getUserID());
        if (userMapper.deleteByNick(userNick) == 1){
            return true;
        }
        return false;
    }

    /**
        @ClassName UserServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据id查询用户,返回一个用户
    */
    public User queryByID(Integer userID) {
        return userMapper.queryByID(userID);
    }

    /**
        @ClassName UserServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据昵称查询用户信息,返回一个用户
    */
    public User queryByNick(String userNick) {
        return userMapper.queryByNick(userNick);
    }

    /**
        @ClassName UserServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 查询全部用户数据，返回用户的集合
    */
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    /**
        @ClassName UserServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过用户id修改用户信息,返回修改成功与否
    */
    public boolean updateUser(User user) {
        if (userMapper.updateUser(user) == 1){
            return true;
        }
        return false;
    }

    @Override
    public String queryEmailById(Integer userID) {
        return userMapper.queryEmailById(userID);
    }

    public User getUserByEmail(String userEmail){
        return userMapper.getUserByEmail(userEmail);
    }



    public boolean changeUser(Integer userID,
                       String userNick,
                       String userPassword,
                       String userEmail,
                       String userAddr,
                       String userContact){
        if (userMapper.changeUser(userID,userNick,userPassword,userEmail,userAddr,userContact) == 1){
            return true;
        }else {
            return false;
        }
    }
}
