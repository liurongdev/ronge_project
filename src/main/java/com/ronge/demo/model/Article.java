package com.ronge.demo.model;


import lombok.Data;

import java.util.Date;


@Data
public class Article {

    private long id;


    /**
     * 文章标题
     */
    private String title;


    /**
     * 文章类别
     */
    private String Label;


    /**
     * 文章子类别
     */
    private String subLabel;


    /**
     * 文章作者
     */
    private String authorId;


    /**
     * 点赞数量
     */
    private int likeCounts;


    /**
     * 评论数量
     */
    private int commentCounts;


    /**
     * 文章内容
     */
    private String content;


    /**
     * 文章创建时间
     */
    private Date createTime;


    /**
     * 文章更新时间
     */
    private Date updateTime;

    /**
     *  文章发表时间
     *  1、刚刚
     *  2、几分钟前
     *  3、几小时前
     */
    private String aliasCreateTime;

}
