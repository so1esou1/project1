package com.youcloud.pojo;



import java.io.Serializable;
import java.util.Date;

/**
    @ClassName 具体资源的实体类
    @author so1esou1
    @Date 2021.1.4
    @TODO
*/

public class Sources implements Serializable {
    /**
        资源的地址，唯一标识
     */
    private String sourceAddr;
    /**
        资源的名称
     */
    private String sourceName;
    /**
        资源的大小
     */
    private Integer sourceSize;
    /**
        资源的类型
     */
    private String sourceType;
    /**
        资源上传者的id
     */
    private Integer sourceUserID;
    /**
     * 一个资源一定对应一个文件夹
     */
    private String sourceFileName;

    /**
     *  上传时间
     */
    private Date uploadTime;

    /**
     * 后缀
     */
    private String postFix;


    public Sources() {
    }

    public Sources(String sourceAddr, String sourceName, Integer sourceSize, String sourceType, Integer sourceUserID, String sourceFileName, Date uploadTime, String postFix) {
        this.sourceAddr = sourceAddr;
        this.sourceName = sourceName;
        this.sourceSize = sourceSize;
        this.sourceType = sourceType;
        this.sourceUserID = sourceUserID;
        this.sourceFileName = sourceFileName;
        this.uploadTime = uploadTime;
        this.postFix = postFix;
    }

    public Sources(String sourceAddr, String sourceName, Integer sourceSize, String sourceType, Integer sourceUserID, String sourceFileName, Date uploadTime) {
        this.sourceAddr = sourceAddr;
        this.sourceName = sourceName;
        this.sourceSize = sourceSize;
        this.sourceType = sourceType;
        this.sourceUserID = sourceUserID;
        this.sourceFileName = sourceFileName;
        this.uploadTime = uploadTime;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public String getSourceAddr() {
        return sourceAddr;
    }

    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Integer getSourceSize() {
        return sourceSize;
    }

    public String getPostFix() {
        return postFix;
    }

    public void setPostFix(String postFix) {
        this.postFix = postFix;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setSourceSize(Integer sourceSize) {
        this.sourceSize = sourceSize;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getSourceUserID() {
        return sourceUserID;
    }

    public void setSourceUserID(Integer sourceUserID) {
        this.sourceUserID = sourceUserID;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "sourceAddr='" + sourceAddr + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", sourceSize='" + sourceSize + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", sourceUserID=" + sourceUserID +
                '}';
    }
}
