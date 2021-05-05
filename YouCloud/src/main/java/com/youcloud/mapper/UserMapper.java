package com.youcloud.mapper;

import com.youcloud.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author so1esou1
 * @ClassName   用户的dao层接口
 * @Date 2021.1.4
 * @TODO
 */
@Mapper
public interface UserMapper {
    /**
     * 增加一个用户
     * @return   更改的行数
     */
    int addUser(User user);

    /**
     * 根据id删除用户
     * @param userID
     * @return  更改的行数
     */
    int deleteByID(Integer userID);

    /**
     * 根据昵称删除用户
     * @param userNick
     * @return  更改的行数
     */
    int deleteByNick(String userNick);

    /**
     * 根据id查询用户
     * @param userID
     * @return  用户对象
     */
    User queryByID(Integer userID);

    /**
     *  根据昵称查询用户信息
     * @param userNick
     * @return
     */
    User queryByNick(String userNick);

    /**
     * 查询全部数据
     * @return
     */
    List<User> queryAll();

    /**
     * 通过用户id修改用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 通过用户id获取其邮箱
     * @param userID
     * @return
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
    Integer changeUser(@Param("userID") Integer userID,
                       @Param("userNick") String userNick,
                       @Param("userPassword") String userPassword,
                       @Param("userEmail") String userEmail,
                       @Param("userAddr") String userAddr,
                       @Param("userContact") String userContact);
}
