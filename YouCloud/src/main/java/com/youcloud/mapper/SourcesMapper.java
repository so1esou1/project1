package com.youcloud.mapper;

import com.youcloud.pojo.Sources;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author so1esou1
 * @ClassName 资源的dao层接口
 * @Date 2021.1.4
 * @TODO
 */
@Mapper

public interface SourcesMapper {
    /**
     * 添加资源
     * @param sources
     * @return
     */
    Integer addSource(Sources sources);

    /**
     * 根据资源的名字修改资源的信息
     * @param sourceName
     * @return
     */
    Integer updateSource(String sourceName);

    /**
     * 根据资源的名字，删除资源
     * @param sourceName
     * @return
     */
    Integer deleteSource(String sourceName);

    /**
     * 根据文件夹的名字删除资源
     * @param sourceFileName
     * @return
     */
    Integer deleteSourcesByHolder(String sourceFileName);

    /**
     * 根据文件夹的名字查询资源
     * @param sourceFileName
     * @return
     */
    List<Sources> querySourcesByHolder(String sourceFileName);

    /**
     * 根据资源的名字查询资源
     * @param sourceName
     * @return
     */
    Sources querySourcesByName(String sourceName);

    /**
     *  通过用户id查询其上传的文件
     * @param userID
     * @return
     */
    List<Sources> querySourcesByID(Integer userID);

    /**
     * 通过用户id统计其上传的资源总数
     * @param userID
     * @return
     */
    int countByUserID(Integer userID);

    /**
     *  通过文件夹名字统计其拥有的资源数
     * @param sourceFileName
     * @return
     */
    int countByHolder(String sourceFileName);

    /**
     * 模糊查询资源
     * @return
     */
    List<Sources> fuzzyQuerySource(String sn);


    /**
     * 修改资源名字
     * @param oName
     * @param nName
     * @return
     */
    Integer renameSource(@Param("oName") String oName,@Param("nName") String nName);
}
