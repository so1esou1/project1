package com.youcloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.youcloud.pojo.FileHolder;
import com.youcloud.service.FileHolderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author so1esou1
 * @ClassName 文件夹service层接口的实现类
 * @Date 2021.1.4
 * @TODO
 */
@Service
public class FileHolderServiceImpl extends BaseService implements FileHolderService {

    /**
        @ClassName FileHolderServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 添加一个文件夹,返回成功的布尔值
    */
    public boolean addFileHolder(FileHolder fileHolder) {
        if (fileHolderMapper.addFileHolder(fileHolder) == 1){
            return true;
        }
        return false;
    }

    /**
        @ClassName FileHolderServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过朋友圈id查询文件夹,返回文件夹的列表
    */
    public List<FileHolder> queryFileHolderByCircle(Integer fileCircleID) {
        return fileHolderMapper.queryFileHolderByCircle(fileCircleID);
    }

    /**
        @ClassName FileHolderServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过文件夹名字查询文件夹,返回一个文件夹
    */
    public FileHolder queryFileHolderByFileHolder(String fileName) {
        return fileHolderMapper.queryFileHolderByFileHolder(fileName);
    }

    /**
        @ClassName FileHolderServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过仓库名删除仓库,返回成功与否的值
    */
    public boolean deleteFileHolderByName(String fileName) {
        if (fileHolderMapper.deleteFileHolderByName(fileName) == 1){
            return true;
        }
        return false;
    }

    /**
        @ClassName FileHolderServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过仓库名字查询仓库的容量,返回一个数值
    */
    public Integer queryCapByName(String fileName) {
        return fileHolderMapper.queryCapByName(fileName);
    }

    /**
        @ClassName FileHolderServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过仓库名修改仓库,返回修改成功与否
    */
    public boolean updateHolderByName(String fileName) {
        if (fileHolderMapper.updateHolderByName(fileName) == 1){
            return true;
        }
        return false;
    }

    @Override
    public Integer addHolderCap(String fileName, Integer size) {
        return fileHolderMapper.addHolderCap(fileName,size);
    }

    @Override
    public Integer subHolderCap(String fileName, Integer size) {
        return fileHolderMapper.subHolderCap(fileName,size);
    }



    @Override
    public List<FileHolder> fuzzyQueryHolder(String fileName) {
        return fileHolderMapper.fuzzyQueryHolder(fileName);
    }



    @Override
    public PageInfo<FileHolder> findFileHolderByPage(int currentPage, int pageSize, Integer circleID) {
        PageHelper.startPage(currentPage, pageSize);
        List<FileHolder> allMessages = fileHolderMapper.queryFileHolderByCircle(circleID);

        PageInfo<FileHolder> pageInfo = new PageInfo<>(allMessages);
        return pageInfo;
    }


    @Override
    public boolean renameHolder(String oName, String nName) {
        if (fileHolderMapper.renameHolder(oName,nName) == 1){
            return true;
        }else {
            return false;
        }
    }
}
