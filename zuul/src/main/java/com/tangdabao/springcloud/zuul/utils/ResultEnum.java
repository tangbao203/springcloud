package com.tangdabao.springcloud.zuul.utils;

/**
 * 返回结果枚举
 */
public enum ResultEnum {
    SUCCESS(1,"成功"),
    SYSTEM_ERROR(0,"系统错误"),
    ERROR_PARAM(-3,"参数错误"),
    NOT_AUTH(-4,"未授权");

    private int code;
    private String msg;

    ResultEnum(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
