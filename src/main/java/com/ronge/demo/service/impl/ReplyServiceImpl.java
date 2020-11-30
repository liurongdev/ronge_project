package com.ronge.demo.service.impl;

import com.ronge.demo.dao.ReplyDao;
import com.ronge.demo.model.Reply;
import com.ronge.demo.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author liurong
 * @date 2020/10/4 21:03
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    private static final Logger logger = LoggerFactory.getLogger(ReplyServiceImpl.class);

    private final String DO_LIKE = "do_like";

    private final String UNDO_LIKE = "undo_like";

    private final String DO_DISLIKE = "do_dislike";

    private final String UNDO_DISLIKE = "undo_dislike";

    @Autowired
    private ReplyDao replyDao;

    @Override
    public void addReply(Reply reply) {
        int rows = replyDao.addReply(reply);
        logger.info("添加回复成功:rows:{},reply.id:{}", rows, reply.getId());
    }

    @Override
    public void deleteReplyById(int replyId) {
        int rows = replyDao.deleteById(replyId);
        logger.info("删除回复成功:rows:{},reply:{}", rows, replyId);
    }


    @Override
    public void doAction(int replyId, String operateType) {
        if(!(Objects.equals(DO_LIKE,operateType) || Objects.equals(operateType,UNDO_LIKE)
                || (Objects.equals(DO_DISLIKE,operateType)) || Objects.equals(UNDO_DISLIKE,operateType))){
            throw new IllegalArgumentException("please import reply valid operateType param...");
        }
        replyDao.doAction(replyId,operateType);
    }

    @Override
    public List<Reply> getItemsByCommentId(int commentId) {
        return replyDao.getItemsByCommentId(commentId);
    }
}
