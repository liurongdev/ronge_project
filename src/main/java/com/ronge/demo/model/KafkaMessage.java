package com.ronge.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class KafkaMessage implements Serializable {

    private static final long serializableId = 6678420965611108427L;

    private int id;

    private String source;

    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
