package com.youcloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youcloud.pojo.Circle;
import com.youcloud.pojo.FileHolder;
import com.youcloud.service.CircleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author so1esou1
 * @ClassName 朋友圈service层接口实现类
 * @Date 2021.1.4
 * @TODO
 */
@Service
public class CircleServiceImpl extends BaseService implements CircleService {

    /**
        @ClassName CircleServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过用户id查询拥有的朋友圈,返回朋友圈列表
    */
    public List<Circle> queryCircleID(Integer userID) {
        return circleMapper.queryCircleID(userID);
    }

    @Override
    public Circle queryCircleByName(String circleName) {

        return circleMapper.queryCircleByName(circleName);
    }

    /**
     * 计算朋友圈已使用的容量
     * @param circleName
     * @return
     */
    @Override
    public Integer queryCircleCap(String circleName) {
        //拿到这个朋友圈所有的文件夹
        Circle circle = circleMapper.queryCircleByName(circleName);
        List<FileHolder> fileHolders = fileHolderMapper.queryFileHolderByCircle(circle.getCircleID());

        Integer sumCap = 0;
        if (null != fileHolders){
            //遍历所有的文件夹:
            for (int i = 0;i < fileHolders.size();i ++){
                int cap = fileHolderMapper.queryCapByName(fileHolders.get(i).getFileName());
                sumCap += cap;
            }

        }
        return sumCap;
    }

    /**
        @ClassName CircleServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过用户名字查询朋友圈的名字,返回名字的列表
    */
    public List<String> queryCircleName(String userName) {
        return circleMapper.queryCircleName(userName);
    }

    /**
        @ClassName CircleServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 创建一个朋友圈,返回成功与否的布尔值
    */
    public boolean addCircle(Circle circle) {
        if (circleMapper.addCircle(circle) == 1){
            usersCirclesMapper.addCircleUsersCircles(circle.getCircleID());
            return true;
        }
        return false;
    }

    /**
        @ClassName CircleServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据朋友圈名字删除一个朋友圈,返回成功与否的布尔值
    */
    public boolean deleteCircleByName(String circleName) {
        Integer circleID = circleMapper.queryCircleByName(circleName).getCircleID();
        usersCirclesMapper.deleteUsersCirclesByCircle(circleID);
        if (circleMapper.deleteCircleByName(circleName) == 1){
            return true;
        }
        return false;
    }

    /**
        @ClassName CircleServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过朋友圈id删除朋友圈,返回成功与否的布尔值
    */
    public boolean deleteCircleByID(Integer circleID) {
        usersCirclesMapper.deleteUsersCirclesByCircle(circleID);
        if (circleMapper.deleteCircleByID(circleID) == 1){

            return true;
        }
        return false;
    }

    /**
        @ClassName CircleServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 修改朋友圈,返回成功与否的布尔值
    */
    public boolean updateCircle(Integer circleID) {
        if (circleMapper.updateCircle(circleID) == 1){
            return true;
        }
        return false;
    }

    @Override
    public Circle queryCircleByID(Integer circleID) {
        return circleMapper.queryCircleByID(circleID);
    }


    /**
     * 分页查询朋友圈，通过用户ID实现
     * @param currentPage
     * @param pageSize
     * @param userID
     * @return
     */
    public PageInfo<Circle> findCirclesByPage(int currentPage, int pageSize, Integer userID){
        PageHelper.startPage(currentPage, pageSize);
        List<Circle> allMessages = circleMapper.queryCircleID(userID);
        PageInfo<Circle> pageInfo = new PageInfo<>(allMessages);
        return pageInfo;
    }

    @Override
    public Integer renameCircle(Integer circleID, String circleName) {
        return circleMapper.renameCircle(circleID,circleName);
    }

    @Override
    public boolean addCircleSize(Integer circleID, Integer size) {
        if (circleMapper.addCircleSize(circleID,size) == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean subCircleSize(Integer circleID, Integer size) {
        if (circleMapper.subCircleSize(circleID,size) == 1){
            return true;
        }
        return false;
    }
}
