package com.tangdabao.springcloud.zuul.utils;

import java.io.Serializable;

public class BaseResultVo<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    /**
     * 给序列化反序列化的用
     */
    public BaseResultVo(){}

    public BaseResultVo(ResultEnum status){
        code=status.getCode();
        msg=status.getMsg();
    }

    public BaseResultVo(int code, T data){
        this.code=code;
        this.data=data;
    }

    public BaseResultVo(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    /**
     * 直接返回成功
     * @param code
     * @param data
     * @return
     */
    public static BaseResultVo toSuccess(int code,Object data){
        return new BaseResultVo(code,data);
    }

    /**
     * 直接返回失败
     * @param code
     * @param msg
     * @return
     */
    public static BaseResultVo toFail(int code,String msg){
        return new BaseResultVo(code,msg);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
