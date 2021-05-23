package com.ronge.demo.handler;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.net.BindException;

/**
 * @author liurong
 * @date 2021/5/15 16:21
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //处理form data方式接口调用产生的异常
    @ExceptionHandler(BindException.class)
    public Object bindException(BindException e){
        System.out.println("bindException....");
        e.printStackTrace();
        return null;
    }

    //处理json参数校验产生的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("MethodArgumentNotValidException.....");
        e.printStackTrace();
        return null;
    }


    //处理单个参数校验产生的异常
    @ExceptionHandler(ConstraintViolationException.class)
    public Object constraintViolationException(ConstraintViolationException  e){
        System.out.println("constraintViolationException.....");
        e.printStackTrace();
        return null;
    }




}
