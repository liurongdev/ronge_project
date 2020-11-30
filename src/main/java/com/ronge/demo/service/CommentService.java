package com.ronge.demo.service;

import com.ronge.demo.model.Comment;

import java.util.List;

/**
 * @author liurong
 * @date 2020/10/4 20:02
 */
public interface CommentService {


    void addComment(Comment comment);


    void deleteCommentById(int commentId);

    List<Comment> getItemsByArticleId(long articleId);

    void doAction(int commentId, String operateType);
}
