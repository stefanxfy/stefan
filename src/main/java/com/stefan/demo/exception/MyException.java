package com.stefan.demo.exception;

import com.stefan.demo.enums.ResultEnum; /**
 * Created by Stefan
 * Create Date 2017-11-04/15:08
 */
public class MyException extends RuntimeException {
    private Integer code;

    public MyException(ResultEnum re) {
        super(re.getMsg());
        this.code = re.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
