package com.youcloud.pojo;

import java.io.Serializable;
import java.util.Date;

//单次支付类
public class Payment implements Serializable {

    /**
     * 单次支付的ID
     */
    private Integer payID;

    /**
     * 支付的时间
     */
    private Date payTime;

    /**
     * 支付的说明、备注
     */
    private String payThings;

    /**
     * 支付的用户ID
     */
    private Integer payUserID;

    /**
     * 支付的金额
     */
    private float payCount;

    public Payment() {
    }

    public Payment(Integer payID, Date payTime, String payThings, Integer payUserID, float payCount) {
        this.payID = payID;
        this.payTime = payTime;
        this.payThings = payThings;
        this.payUserID = payUserID;
        this.payCount = payCount;
    }

    public Integer getPayID() {
        return payID;
    }

    public void setPayID(Integer payID) {
        this.payID = payID;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayThings() {
        return payThings;
    }

    public void setPayThings(String payThings) {
        this.payThings = payThings;
    }

    public Integer getPayUserID() {
        return payUserID;
    }

    public void setPayUserID(Integer payUserID) {
        this.payUserID = payUserID;
    }

    public float getPayCount() {
        return payCount;
    }

    public void setPayCount(float payCount) {
        this.payCount = payCount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payID=" + payID +
                ", payTime=" + payTime +
                ", payThings='" + payThings + '\'' +
                ", payUserID=" + payUserID +
                ", payCount=" + payCount +
                '}';
    }
}
