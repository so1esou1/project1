package com.youcloud.service;

import com.github.pagehelper.PageInfo;
import com.youcloud.pojo.Circle;


import java.util.List;

/**
 * @author so1esou1
 * @ClassName   朋友圈的service层接口
 * @Date 2021.1.4
 * @TODO
 */

public interface CircleService {
    /**
        @ClassName CircleService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过用户id查询拥有的朋友圈,返回朋友圈列表
    */
    List<Circle> queryCircleID(Integer userID);


    /**
     * 根据朋友圈名查找朋友圈
     * @param circleName
     * @return
     */
    Circle queryCircleByName(String circleName);


    /**
     * 查找朋友圈已用的容量
     * @param circleName
     * @return
     */
    Integer queryCircleCap(String circleName);

    /**
        @ClassName CircleService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过用户名字查询朋友圈的名字,返回名字的列表
    */
    List<String> queryCircleName(String userName);

    /**
        @ClassName CircleService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 创建一个朋友圈,返回成功与否的布尔值
    */
    boolean addCircle(Circle circle);

    /**
        @ClassName CircleService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据朋友圈名字删除一个朋友圈,返回成功与否的布尔值
    */
    boolean deleteCircleByName(String circleName);

    /**
        @ClassName CircleService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过朋友圈id删除朋友圈,返回成功与否的布尔值
    */
    boolean deleteCircleByID(Integer circleID);

    /**
        @ClassName CircleService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 修改朋友圈,返回成功与否的布尔值
    */
    boolean updateCircle(Integer circleID);


    /**
     * 根据朋友圈ID查找朋友圈
     * @param circleID
     * @return
     */
    Circle queryCircleByID(Integer circleID);


    /**
     * 分页查询朋友圈，通过用户ID实现
     * @param currentPage
     * @param pageSize
     * @param userID
     * @return
     */
    PageInfo<Circle> findCirclesByPage(int currentPage, int pageSize, Integer userID);


    /**
     * 修改朋友圈名字
     * @param circleID
     * @return
     */
    Integer renameCircle(Integer circleID,String circleName);



    /**
     * 朋友圈容量使用量增加
     * @param
     * @return
     */
    boolean addCircleSize(Integer circleID,Integer size);


    /**
     * 减少朋友圈容量使用量
     * @param circleID
     * @param size
     * @return
     */
    boolean subCircleSize(Integer circleID,Integer size);


}
