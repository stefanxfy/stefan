package com.stefan.demo.handle;

import com.stefan.demo.pojo.Result;
import com.stefan.demo.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Stefan
 * Create Date 2017-11-04/14:45
 */
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        return ResultUtil.error(100,e.getMessage());

    }
}
