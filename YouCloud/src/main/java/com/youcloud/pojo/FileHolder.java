package com.youcloud.pojo;

import java.io.Serializable;


/**
 * @author so1esou1
 * @ClassName   文件夹的实体类
 * @Date 2021.1.4
 * @TODO
 */
public class FileHolder implements Serializable {
    /**
     * 文件夹的名字，唯一标识
     */
    private String fileName;
    /**
     * 文件夹已使用的容量
     */
    private Integer fileCap;

    /**
    *   文件夹与朋友圈是多对一，朋友圈有多个文件夹，文件夹必定对应一个朋友圈
     */
    private Integer fileCircleID;


    public FileHolder() {
    }

    public FileHolder(String fileName, Integer fileCap, Integer fileCircleID) {
        this.fileName = fileName;
        this.fileCap = fileCap;

        this.fileCircleID = fileCircleID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileCap() {
        return fileCap;
    }

    public void setFileCap(Integer fileCap) {
        this.fileCap = fileCap;
    }


    public Integer getFileCircleID() {
        return fileCircleID;
    }

    public void setFileCircleID(Integer fileCircleID) {
        this.fileCircleID = fileCircleID;
    }

    @Override
    public String toString() {
        return "FileHolder{" +
                "fileName='" + fileName + '\'' +
                ", fileCap=" + fileCap +
                ", fileCircleID=" + fileCircleID +
                '}';
    }
}
