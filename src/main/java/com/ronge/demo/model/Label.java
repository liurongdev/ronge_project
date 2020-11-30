package com.ronge.demo.model;


import lombok.Data;

@Data
public class Label {


    /**
     * 标签数据库id
     */
    private int id;


    /**
     * 标签名称
     */
    public String name;


    /**
     * 标签值
     */
    public String value;


    /**
     * 标签排序id
     */
    public int orderId;

}
