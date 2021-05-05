package com.youcloud.pojo;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.4.10
 * @TODO 实现用户与朋友圈多对多功能的实体
 */
@Mapper
@Repository
public class UsersCircles {
    private Integer userID;
    private Integer circleID;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getCircleID() {
        return circleID;
    }

    public void setCircleID(Integer circleID) {
        this.circleID = circleID;
    }
}
