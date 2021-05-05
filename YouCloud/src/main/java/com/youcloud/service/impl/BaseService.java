package com.youcloud.service.impl;

import com.youcloud.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author so1esou1
 * @ClassName   所有service实现类的基类，继承以简化自动装配
 * @Date 2021.1.4
 * @TODO
 */
public class BaseService {
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected CircleMapper circleMapper;
    @Autowired
    protected FileHolderMapper fileHolderMapper;
    @Autowired
    protected SourcesMapper sourcesMapper;
    @Autowired
    protected UsersCirclesMapper usersCirclesMapper;
}
