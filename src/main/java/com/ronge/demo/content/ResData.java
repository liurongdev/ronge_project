package com.ronge.demo.content;

import lombok.Data;


@Data
public class ResData<T> {

    private int code;
    private String message;
    private T data;
}
