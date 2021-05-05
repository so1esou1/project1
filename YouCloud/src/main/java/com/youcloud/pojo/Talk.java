package com.youcloud.pojo;

import java.io.Serializable;
import java.util.Date;

//聊天信息
public class Talk implements Serializable {

    /**
     * 发言的ID，唯一标识
     */
    private Integer talkID;

    /**
     * 发言用户的ID
     */
    private Integer talkUserID;

    /**
     * 发言用户的昵称
     */
    private String talkUserNick;

    /**
     * 发言者所在朋友圈的ID
     */
    private Integer talkCircleID;

    /**
     * 发言的内容
     */
    private String talkContent;

    /**
     * 发言的时间
     */
    private Date talkTime;

    public Talk() {
    }

    public Talk(Integer talkID, Integer talkUserID, String talkUserNick, Integer talkCircleID, String talkContent, Date talkTime) {
        this.talkID = talkID;
        this.talkUserID = talkUserID;
        this.talkUserNick = talkUserNick;
        this.talkCircleID = talkCircleID;
        this.talkContent = talkContent;
        this.talkTime = talkTime;
    }

    public Integer getTalkID() {
        return talkID;
    }

    public void setTalkID(Integer talkID) {
        this.talkID = talkID;
    }

    public Integer getTalkUserID() {
        return talkUserID;
    }

    public void setTalkUserID(Integer talkUserID) {
        this.talkUserID = talkUserID;
    }

    public String getTalkUserNick() {
        return talkUserNick;
    }

    public void setTalkUserNick(String talkUserNick) {
        this.talkUserNick = talkUserNick;
    }

    public Integer getTalkCircleID() {
        return talkCircleID;
    }

    public void setTalkCircleID(Integer talkCircleID) {
        this.talkCircleID = talkCircleID;
    }

    public String getTalkContent() {
        return talkContent;
    }

    public void setTalkContent(String talkContent) {
        this.talkContent = talkContent;
    }

    public Date getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Date talkTime) {
        this.talkTime = talkTime;
    }

    @Override
    public String toString() {
        return "Talk{" +
                "talkID=" + talkID +
                ", talkUserID=" + talkUserID +
                ", talkUserNick='" + talkUserNick + '\'' +
                ", talkCircleID=" + talkCircleID +
                ", talkContent='" + talkContent + '\'' +
                ", talkTime=" + talkTime +
                '}';
    }
}
