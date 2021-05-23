package com.ronge.demo.content;

import lombok.Data;

import java.util.List;

@Data
public class ResponseData<T> {


    private int code;
    private String message;
    private List<T> data;



}
