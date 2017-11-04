package com.stefan.demo.exception;

/**
 * Created by Stefan
 * Create Date 2017-11-04/15:08
 */
public class MyException extends RuntimeException {
    private Integer code;

    public MyException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
