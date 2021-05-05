package com.youcloud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.youcloud.pojo.Sources;
import com.youcloud.service.SourcesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author so1esou1
 * @ClassName 资源的service层实现类
 * @Date 2021.1.4
 * @TODO
 */
@Service
public class SourcesServiceImpl extends BaseService implements SourcesService {

    /**
        @ClassName SourcesServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 添加资源,返回成功与否的布尔值
    */
    public boolean addSource(Sources sources) {
        if (sourcesMapper.addSource(sources) == 1){
            return true;
        }
        return false;
    }

    /**
        @ClassName SourcesServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据资源的名字修改资源的信息,返回修改成功与否的布尔值
    */
    public boolean updateSource(String sourceName) {
        if (sourcesMapper.updateSource(sourceName) == 1){
            return true;
        }
        return false;
    }

    /**
        @ClassName SourcesServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据资源的名字，删除资源.返回成功与否的布尔值
    */
    public boolean deleteSource(String sourceName) {
        if (sourcesMapper.deleteSource(sourceName) == 1){
            return true;
        }
        return false;
    }

    /**
        @ClassName SourcesServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据文件夹的名字删除资源,返回成功与否的布尔值
    */
    public boolean deleteSourcesByHolder(String sourceFileName) {
        if (sourcesMapper.deleteSourcesByHolder(sourceFileName) == 1){
            return true;
        }
        return false;
    }

    /**
        @ClassName SourcesServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据文件夹的名字查询资源,返回资源文件的列表
    */
    public List<Sources> querySourcesByHolder(String sourceFileName) {
        return sourcesMapper.querySourcesByHolder(sourceFileName);
    }

    /**
        @ClassName SourcesServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据资源的名字查询资源,返回资源文件
         * @return
    */
    public Sources querySourcesByName(String sourceName) {
        return sourcesMapper.querySourcesByName(sourceName);
    }

    /**
     @ClassName SourcesServiceImpl.java
     @author so1esou1
     @Date 2021.1.4
     @TODO 通过用户id查询其上传的文件名
      * @return
     */
    @Override
    public List<Sources> querySourcesByID(Integer userID) {
        return sourcesMapper.querySourcesByID(userID);
    }


    /**
        @ClassName SourcesServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过用户id统计其上传的资源总数
    */
    @Override
    public int countByUserID(Integer userID) {
        return sourcesMapper.countByUserID(userID);
    }

    /**
        @ClassName SourcesServiceImpl.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过文件夹名字统计其拥有的资源数
    */
    @Override
    public int countByHolder(String sourceFileName) {
        return sourcesMapper.countByHolder(sourceFileName);
    }




    @Override
    public List<Sources> fuzzyQuerySource(String sn) {
        return sourcesMapper.fuzzyQuerySource(sn);
    }


    /**
     * 计算文件夹中已使用空间大小的方法
     * @param fileName
     * @return
     */
    @Override
    public Integer sumCap(String fileName) {
        Integer sum = 0;
        List<Sources> sourcesList = sourcesMapper.querySourcesByHolder(fileName);
        for (int i = 0;i < sourcesList.size();i ++){
            Sources sources = sourcesList.get(i);
            sum += Integer.valueOf(sources.getSourceSize());
        }
        return sum;
    }


    /**
     * 分页查询实现，通过用户ID查询时
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageInfo<Sources> findSourcesByPage(int currentPage, int pageSize,Integer userID) {
        PageHelper.startPage(currentPage, pageSize);
        List<Sources> allMessages = sourcesMapper.querySourcesByID(userID);
        PageInfo<Sources> pageInfo = new PageInfo<>(allMessages);
        return pageInfo;
    }


    /**
     * 分页查询资源，通过文件夹名字实现
     * @param currentPage
     * @param pageSize
     * @param fileName
     * @return
     */
    public PageInfo<Sources> findFSourcesByPage(int currentPage, int pageSize,String fileName) {
        PageHelper.startPage(currentPage, pageSize);
        List<Sources> allMessages = sourcesMapper.querySourcesByHolder(fileName);

        PageInfo<Sources> pageInfo = new PageInfo<>(allMessages);
        return pageInfo;
    }

    @Override
    public boolean renameSource(String oName, String nName) {

        if (sourcesMapper.renameSource(oName,nName) == 1){

            return true;
        }
        else {
            System.out.println("wesas");
            return false;
        }
    }

}
