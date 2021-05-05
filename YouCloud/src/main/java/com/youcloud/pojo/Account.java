package com.youcloud.pojo;

import java.io.Serializable;

//用户的账户类
public class Account implements Serializable {
    /**
     * 账户所有者的ID
     */
    private Integer accountUserID;

    /**
     * 账户的余额
     */
    private float balance;

    /**
     * 已支付的金额
     */
    private float payed;

    public Account() {
    }

    public Account(Integer accountUserID, float balance, float payed) {
        this.accountUserID = accountUserID;
        this.balance = balance;
        this.payed = payed;
    }

    public Integer getAccountUserID() {
        return accountUserID;
    }

    public void setAccountUserID(Integer accountUserID) {
        this.accountUserID = accountUserID;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getPayed() {
        return payed;
    }

    public void setPayed(float payed) {
        this.payed = payed;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountUserID=" + accountUserID +
                ", balance=" + balance +
                ", payed=" + payed +
                '}';
    }
}
