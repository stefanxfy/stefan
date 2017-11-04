package com.stefan.demo.handle;

import com.stefan.demo.exception.MyException;
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
        if (e instanceof MyException){
            MyException myException=(MyException) e;
            return ResultUtil.error(((MyException) e).getCode(),e.getMessage());

        }else{
            return ResultUtil.error(103,"未知错误");

        }


    }
}
