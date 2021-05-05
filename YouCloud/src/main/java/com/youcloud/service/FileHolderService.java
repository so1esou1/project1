package com.youcloud.service;

import com.github.pagehelper.PageInfo;

import com.youcloud.pojo.FileHolder;



import java.util.List;

/**
 * @author so1esou1
 * @ClassName 文件夹的service层接口
 * @Date 2021.1.4
 * @TODO
 */

public interface FileHolderService {

    /**
        @ClassName FileHolderService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 添加一个文件夹,返回成功的布尔值
    */
    public boolean addFileHolder(FileHolder fileHolder);

    /**
        @ClassName FileHolderService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过朋友圈id查询文件夹,返回文件夹的列表
    */
    public List<FileHolder> queryFileHolderByCircle(Integer fileCircleID);

    /**
        @ClassName FileHolderService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过文件夹名字查询文件夹,返回一个文件夹
    */
    public FileHolder queryFileHolderByFileHolder(String fileName);

    /**
        @ClassName FileHolderService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过仓库名删除仓库,返回成功与否的值
    */
    public boolean deleteFileHolderByName(String fileName);

    /**
        @ClassName FileHolderService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过仓库名字查询仓库的容量,返回一个数值
    */
    public Integer queryCapByName(String fileName);

    /**
        @ClassName FileHolderService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过仓库名修改仓库,返回修改成功与否
    */
    public boolean updateHolderByName(String fileName);


    /**
        @ClassName FileHolderService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过名字增加仓库容量
    */
    public Integer addHolderCap(String fileName,Integer size);


    /**
        @ClassName FileHolderService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过名字减少仓库容量
    */
    public Integer subHolderCap(String fileName,Integer size);




    /**
     * 模糊查询文件夹的名字
     * @param fn
     * @return
     */
    public List<FileHolder> fuzzyQueryHolder(String fileName);


    /**
     * 分页查询文件夹，通过朋友圈ID实现
     * @param currentPage
     * @param pageSize
     * @param
     * @return
     */
    PageInfo<FileHolder> findFileHolderByPage(int currentPage, int pageSize, Integer circleID);


    /**
     * 重命名文件夹
     * @param oName
     * @param nName
     * @return
     */
    public boolean renameHolder(String oName,String nName);
}
