package com.youcloud.pojo.dto;

import org.springframework.data.relational.core.sql.In;

/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.1.4
 * @TODO
 */
public class ResponseDTO {
    /**
     * 服务器返回给客户端的消息
     */
    private String msg;
    /**
     * 返回的结果
     */
    private Boolean res;
    /**
     * 返回的数据
     */
    private Object data;
    /**
     * 返回的状态码.默认值是200
     */
    private Integer status = 200;
    /**
     * 错误代码
     */
    private Integer error;

    public ResponseDTO() {
    }

    public ResponseDTO(String msg, Boolean res, Object data, Integer status, Integer error) {
        this.msg = msg;
        this.res = res;
        this.data = data;
        this.status = status;
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getRes() {
        return res;
    }

    public void setRes(Boolean res) {
        this.res = res;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "msg='" + msg + '\'' +
                ", res=" + res +
                ", data=" + data +
                ", status=" + status +
                ", error=" + error +
                '}';
    }

    /**
     * 返回简单的成功消息
     * @param msg
     * @return
     */
    public static ResponseDTO ok(String msg,Object data){
        return new ResponseDTO(msg,true,null,200,null);
    }

    /**
     * 返回简单的错误消息
     * @param msg
     * @return
     */
    public static ResponseDTO fail1(String msg){
        return new ResponseDTO(msg,false,null,null,null);
    }

    /**
     * 返回复杂的错误消息
     * @param msg
     * @param data
     * @param status
     * @param error
     * @return
     */
    public static ResponseDTO fail2(String msg, Object data, Integer status,Integer error){
        return new ResponseDTO(msg,false,data,status,error);
    }
}


