package com.youcloud.pojo;



import java.io.Serializable;
import java.util.List;

/**
    @ClassName 朋友圈实体类
    @author so1esou1
    @Date 2021.1.4
    @TODO
*/

public class Circle implements Serializable {
    /**
        朋友圈的id，唯一标识
     */
    private Integer circleID;
    /**
        朋友圈的名字
     */
    private String circleName;
    /**
        进群的密码
     */
    private String circlePassword;
    /**
        朋友圈内用户的名字
     */
    private List<User> circleUser;
    /**
     *  朋友圈中拥有的总储存容量
     */
    private Integer cap;
    /**
     * 朋友圈已用内存
     */
    private Integer capUsed;

    /**
     * 朋友圈的等级，共3级，容量从小到大，刚开始为1级
     */
    private Integer capLevel;
    /**
     *  朋友圈中已创建的文件夹
     */
    private List<FileHolder> fileHolders;
    /**
     *  创建者的名字，创建者的权限为1
     */
    private String foundName;

    /**
     * 朋友圈总用户数
     */
    private Integer circleUserCount;




    public Circle() {
    }

    public Circle(Integer circleID, String circleName, String circlePassword, List<User> circleUser, Integer cap, Integer capUsed, Integer capLevel, List<FileHolder> fileHolders, String foundName, Integer circleUserCount) {
        this.circleID = circleID;
        this.circleName = circleName;
        this.circlePassword = circlePassword;
        this.circleUser = circleUser;
        this.cap = cap;
        this.capUsed = capUsed;
        this.capLevel = capLevel;
        this.fileHolders = fileHolders;
        this.foundName = foundName;
        this.circleUserCount = circleUserCount;
    }

    public Integer getCircleID() {
        return circleID;
    }

    public void setCircleID(Integer circleID) {
        this.circleID = circleID;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getCirclePassword() {
        return circlePassword;
    }

    public void setCirclePassword(String circlePassword) {
        this.circlePassword = circlePassword;
    }

    public List<User> getCircleUser() {
        return circleUser;
    }

    public void setCircleUser(List<User> circleUser) {
        this.circleUser = circleUser;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public Integer getCapUsed() {
        return capUsed;
    }

    public void setCapUsed(Integer capUsed) {
        this.capUsed = capUsed;
    }

    public Integer getCapLevel() {
        return capLevel;
    }

    public void setCapLevel(Integer capLevel) {
        this.capLevel = capLevel;
    }

    public List<FileHolder> getFileHolders() {
        return fileHolders;
    }

    public void setFileHolders(List<FileHolder> fileHolders) {
        this.fileHolders = fileHolders;
    }

    public String getFoundName() {
        return foundName;
    }

    public void setFoundName(String foundName) {
        this.foundName = foundName;
    }

    public Integer getCircleUserCount() {
        return circleUserCount;
    }

    public void setCircleUserCount(Integer circleUserCount) {
        this.circleUserCount = circleUserCount;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "circleID=" + circleID +
                ", circleName='" + circleName + '\'' +
                ", circlePassword='" + circlePassword + '\'' +
                ", circleUser=" + circleUser +
                ", cap=" + cap +
                ", capUsed=" + capUsed +
                ", capLevel=" + capLevel +
                ", fileHolders=" + fileHolders +
                ", foundName='" + foundName + '\'' +
                ", circleUserCount=" + circleUserCount +
                '}';
    }
}
