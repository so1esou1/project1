package com.youcloud.mapper;

import com.youcloud.pojo.Circle;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author so1esou1
 * @ClassName 朋友圈的dao层接口
 * @Date 2021.1.4
 * @TODO
 */
@Mapper

public interface CircleMapper {
    /**
     * 通过用户id查询拥有的朋友圈
     * @param userID 用户实体类
     * @return
     */
    List<Circle> queryCircleID(Integer userID);

    /**
     * 通过用户名字查询朋友圈的名字
     * @param userName  用户实体类
     * @return
     */
    List<String> queryCircleName(String userName);

    /**
     *  创建一个朋友圈
     * @param circle
     * @return  改变的行数
     */
    Integer addCircle(Circle circle);

    /**
     * 根据朋友圈名字删除一个朋友圈
     * @param circleName
     * @return
     */
    Integer deleteCircleByName(String circleName);

    /**
     * 通过朋友圈id删除朋友圈
     * @param circleID
     * @return
     */
    Integer deleteCircleByID(Integer circleID);

    /**
     * 修改朋友圈
     * @param circleID
     * @return  改变的行数
     */
    Integer updateCircle(Integer circleID);

    /**
     * 修改朋友圈名字
     * @param circleID
     * @return
     */
    Integer renameCircle(@Param("circleID") Integer circleID,@Param("circleName") String circleName);


    /**
     * 根据朋友圈名查找朋友圈
     * @param circleName
     * @return
     */
    Circle queryCircleByName(String circleName);

    /**
     * 根据朋友圈ID查找朋友圈
     * @param circleID
     * @return
     */
    Circle queryCircleByID(Integer circleID);


    /**
     * 朋友圈容量使用量增加
     * @param
     * @return
     */
    Integer addCircleSize(@Param("circleID") Integer circleID,@Param("size") Integer size);


    /**
     * 减少朋友圈容量使用量
     * @param circleID
     * @param size
     * @return
     */
    Integer subCircleSize(@Param("circleID") Integer circleID,@Param("size") Integer size);
}
