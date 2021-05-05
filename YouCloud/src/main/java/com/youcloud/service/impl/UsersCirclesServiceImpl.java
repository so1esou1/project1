package com.youcloud.service.impl;

import com.youcloud.service.UsersCirclesService;


import org.springframework.stereotype.Service;

/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.4.10
 * @TODO
 */
@Service
public class UsersCirclesServiceImpl extends BaseService implements UsersCirclesService {

    /**
     * 增加对应联系方式
     * @param userID
     * @param circleID
     */
    @Override
    public void addUsersCircles(Integer userID, Integer circleID) {
        usersCirclesMapper.addUsersCircles(userID,circleID);
    }

    @Override
    public void deleteUsersCircles(Integer userID, Integer circleID) {
        usersCirclesMapper.deleteUsersCircles(userID,circleID);
    }


}
