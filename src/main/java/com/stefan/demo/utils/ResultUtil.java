package com.stefan.demo.utils;

import com.stefan.demo.enums.ResultEnum;
import com.stefan.demo.pojo.Result;

/**
 * Created by Stefan
 * Create Date 2017-11-04/13:38
 */
public class ResultUtil {
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;

    }

    public static Result success() {
        return success(null);
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }
    public static  Result error(ResultEnum re){
        Result result = new Result();
        result.setCode(ResultEnum.UNKNOW_ERROR.getCode());
        result.setMsg(ResultEnum.UNKNOW_ERROR.getMsg());
        return result;
    }
}
