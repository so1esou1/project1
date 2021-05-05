package com.youcloud.service;

import com.github.pagehelper.PageInfo;
import com.youcloud.pojo.Sources;


import java.util.List;

/**
 * @author so1esou1
 * @ClassName 资源的service层接口
 * @Date 2021.1.4
 * @TODO
 */

public interface SourcesService {
    /**
        @ClassName SourcesService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 添加资源,返回成功与否的布尔值
    */
    boolean addSource(Sources sources);

    /**
        @ClassName SourcesService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据资源的名字修改资源的信息,返回修改成功与否的布尔值
    */
    boolean updateSource(String sourceName);

    /**
        @ClassName SourcesService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据资源的名字，删除资源.返回成功与否的布尔值
    */
    boolean deleteSource(String sourceName);

    /**
        @ClassName SourcesService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据文件夹的名字删除资源,返回成功与否的布尔值
    */
    boolean deleteSourcesByHolder(String sourceFileName);

    /**
        @ClassName SourcesService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据文件夹的名字查询资源,返回资源文件的列表
    */
    List<Sources> querySourcesByHolder(String sourceFileName);

    /**
        @ClassName SourcesService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据资源的名字查询资源,返回资源文件的列表
         * @return
    */
    Sources querySourcesByName(String sourceName);

    /**
        @ClassName SourcesService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过用户id查询其上传的文件名
         * @return
    */
    List<Sources> querySourcesByID(Integer userID);

    /**
        @ClassName SourcesService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过用户id统计其上传的资源总数
    */
    int countByUserID(Integer userID);

    /**
        @ClassName SourcesService.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 通过文件夹名字统计其拥有的资源数
    */
    int countByHolder(String sourceFileName);





    /**
     * 模糊查询资源
     * @return
     */
    List<Sources> fuzzyQuerySource(String sn);

    /**
     * 获取文件夹中资源的总占用大小
     * @param fileName
     * @return
     */
    public Integer sumCap(String fileName);


    /**
     * 分页查询实现
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageInfo<Sources> findSourcesByPage(int currentPage, int pageSize,Integer userID);



    public PageInfo<Sources> findFSourcesByPage(int currentPage, int pageSize,String fileName);


    /**
     * 修改资源名字
     * @param oName
     * @param nName
     * @return
     */
    boolean renameSource(String oName,String nName);
}
