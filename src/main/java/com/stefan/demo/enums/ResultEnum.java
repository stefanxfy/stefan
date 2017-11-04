package com.stefan.demo.enums;

/**
 * Created by Stefan
 * Create Date 2017-11-04/15:27
 */
public enum ResultEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    SUCCESS(0, "成功"),
    ERROR(1,""),
    YOUNG(100,"你正处于职业发展期"),
    OLD(101,"你马上就要退休了"),
    ;
    private  Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
