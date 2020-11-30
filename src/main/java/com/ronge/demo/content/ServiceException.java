package com.ronge.demo.content;

public class ServiceException extends RuntimeException {

    public ServiceException(String reason){
        super(reason);
    }
}
