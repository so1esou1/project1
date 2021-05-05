package com.youcloud.mapper;


import com.youcloud.pojo.FileHolder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author so1esou1
 * @ClassName 文件夹的dao层接口
 * @Date 2021.1.4
 * @TODO
 */
@Mapper

public interface FileHolderMapper {
    /**
     *  添加一个文件夹
     * @param fileHolder
     * @return  改变的行数
     */
    public Integer addFileHolder(FileHolder fileHolder);

    /**
     * 通过朋友圈id查询文件夹
     * @param fileCircleID
     * @return
     */
    public List<FileHolder> queryFileHolderByCircle(Integer fileCircleID);

    /**
     * 通过文件夹名字查询文件夹
     * @param fileName
     * @return
     */
    public FileHolder queryFileHolderByFileHolder(String fileName);

    /**
     * 通过仓库名删除仓库
     * @param fileName
     * @return
     */
    public Integer deleteFileHolderByName(String fileName);

    /**
     * 通过仓库名字查询仓库的容量
     * @param fileName
     * @return
     */
    public Integer queryCapByName(String fileName);


    /**
     * 通过仓库名修改仓库
     * @param fileName
     * @return  返回改变的行数
     */
    public Integer updateHolderByName(String fileName);


    /**
     *  通过名字增加仓库容量
     * @param fileName    仓库名
     * @param size          增加的大小
     * @return
     */
    public Integer addHolderCap(@Param("fileName") String fileName,@Param("size") Integer size);

    /**
     *  通过名字减少仓库容量
     * @param fileName    仓库名
     * @param size          增加的大小
     * @return
     */
    public Integer subHolderCap(@Param("fileName") String fileName,@Param("size") Integer size);


    /**
     * 模糊查询文件夹的名字
     * @param fn
     * @return
     */
    public List<FileHolder> fuzzyQueryHolder(String fileName);


    /**
     * 重命名文件夹
     * @param oName
     * @param nName
     * @return
     */
    public Integer renameHolder(@Param("oName") String oName,@Param("nName") String nName);

}
