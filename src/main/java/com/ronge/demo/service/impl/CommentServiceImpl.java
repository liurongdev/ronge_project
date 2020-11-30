package com.ronge.demo.service.impl;

import com.ronge.demo.dao.CommentDao;
import com.ronge.demo.model.Comment;
import com.ronge.demo.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author liurong
 * @date 2020/10/4 20:59
 */
@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final String DO_LIKE = "do_like";

    private final String UNDO_LIKE = "undo_like";

    private final String DO_DISLIKE = "do_dislike";

    private final String UNDO_DISLIKE = "undo_dislike";


    @Autowired
    private CommentDao commentDao;


    @Override
    public void addComment(Comment comment){
        comment.setCreateTime(new Date());
        comment.setAuthorId("l00524018");
        int rows = commentDao.addComment(comment);
        logger.info("添加评论成功:rows:{},comment.id:{}",rows,comment.getId());
    }


    @Override
    public void deleteCommentById(int commentId){
        commentDao.deleteByCommentId(commentId);
    }

    @Override
    public List<Comment> getItemsByArticleId(long articleId) {
        return commentDao.getItemsByArticleId(articleId);
    }


    @Override
    public void doAction(int commentId, String operateType) {
        if(!(Objects.equals(DO_LIKE,operateType) || Objects.equals(operateType,UNDO_LIKE)
            || (Objects.equals(DO_DISLIKE,operateType)) || Objects.equals(UNDO_DISLIKE,operateType))){
            throw new IllegalArgumentException("please import comment valid operateType param...");
        }
        commentDao.doAction(commentId,operateType);
    }

}
