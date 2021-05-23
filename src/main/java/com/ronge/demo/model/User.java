package com.ronge.demo.model;


import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int id;

    private String userName;

    private String passWord;

    private String headImageUrl;

    private Date createDate;

    private Date updateDate;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", headImageUrl='" + headImageUrl + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
