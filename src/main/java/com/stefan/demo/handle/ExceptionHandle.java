package com.stefan.demo.handle;

import com.stefan.demo.enums.ResultEnum;
import com.stefan.demo.exception.MyException;
import com.stefan.demo.pojo.Result;
import com.stefan.demo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Stefan
 * Create Date 2017-11-04/14:45
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof MyException){
            MyException myException=(MyException) e;
            return ResultUtil.error(e.getMessage());

        }else{
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(ResultEnum.UNKNOW_ERROR);

        }


    }
}
