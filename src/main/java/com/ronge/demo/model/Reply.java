package com.ronge.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * @author liurong
 * @date 2020/10/4 20:08
 */
@Data
public class Reply {

    private int id;

    /**
     * 被回复的评论id
     */
    private int commentId;


    /**
     * 文章id
     */
    private long articleId;


    /**
     * 被回复作者id
     */
    private String originAuthorId;


    /**
     * 回复作者id
     */
    private String replyAuthorId;


    /**
     * 回复内容
     */
    private String content;


    /**
     * 创建时间
     */
    private Date createTime;
}
