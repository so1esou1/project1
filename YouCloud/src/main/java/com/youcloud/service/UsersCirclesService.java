package com.youcloud.service;

/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.4.10
 * @TODO
 */
public interface UsersCirclesService {
    /**
     * 增加对应联系方式
     * @param userID
     * @param circleID
     */
    public void addUsersCircles(Integer userID,Integer circleID);


    /**
     * 删除一条对应信息，这里也会更改,对应退出朋友圈的功能
     * @param circleID
     */
    public void deleteUsersCircles(Integer userID,Integer circleID);
}
