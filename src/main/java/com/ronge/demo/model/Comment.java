package com.ronge.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author liurong
 * @date 2020/10/4 20:05
 */
@Data
public class Comment {

    private int id;

    /**
     * 被评论的文章id
     */
    private long articleId;


    /**
     * 评论人
     */
    private String authorId;


    /**
     * 评论内容
     */
    private String content;


    /**
     * 点赞次数
     */
    private int likeCounts;


    /**
     * 被踩的次数
     */
    private int dislikeCounts;


    /**
     * 评论时间
     */
    private Date createTime;


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", authorId='" + authorId + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
