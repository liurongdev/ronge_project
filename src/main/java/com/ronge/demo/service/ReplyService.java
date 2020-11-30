package com.ronge.demo.service;

import com.ronge.demo.model.Reply;

import java.util.List;

/**
 * @author liurong
 * @date 2020/10/4 20:13
 */
public interface ReplyService {


    void addReply(Reply reply);


    void deleteReplyById(int replyId);

    void doAction(int replyId, String operateType);

    List<Reply> getItemsByCommentId(int commentId);
}
