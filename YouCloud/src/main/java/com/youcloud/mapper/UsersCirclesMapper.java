package com.youcloud.mapper;

import com.youcloud.pojo.Circle;
import com.youcloud.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * 只有两个方法
 */
@Mapper

public interface UsersCirclesMapper {
    /**
     * 根据朋友圈ID获取所有的用户
     * @param circleID
     * @return
     */
    public List<User> getUsersByCircleID(Integer circleID);


    /**
     * 根据用户ID获取所有的朋友圈
     * @param userID
     * @return
     */
    public List<Circle> getCirclesByUserID(Integer userID);


    /**
     * 新增用户，单独增加userID，这里也会发生改变
     * @param userID
     */
    public void addUserUsersCircles(Integer userID);




    /**
     * 增加一个朋友圈,单独增加circleID，这里也会更改
     * @param circleID
     */
    public void addCircleUsersCircles(Integer circleID);


    /**
     * 删除一条对应信息，这里也会更改
     * @param circleID
     */
    public void deleteUsersCircles(@Param("userID") Integer userID, @Param("circleID") Integer circleID);

    /**
     * 通过用户名删除联系
     * @param userID
     */
    public void deleteUsersCirclesByUser(Integer userID);

    /**
     * 通过朋友圈名删除联系
     * @param circleID
     */
    public void deleteUsersCirclesByCircle(Integer circleID);

    /**
     * 增加对应联系方式
     * @param userID
     * @param circleID
     */
    public void addUsersCircles(@Param("userID") Integer userID, @Param("circleID") Integer circleID);


    /**
     * 修改联系方式，通过userID
     * @param userID
     */
    public void updateUsersCirclesByUser(@Param("userID") Integer userID, @Param("circleID") Integer circleID);


    /**
     * 修改联系方式，通过circleID
     * @param circleID
     */
    public void updateUsersCirclesByCircle(@Param("userID") Integer userID, @Param("circleID") Integer circleID);
}
