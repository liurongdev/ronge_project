package com.ronge.demo.controller;

import com.ronge.demo.constant.ResponseContants;
import com.ronge.demo.model.Reply;
import com.ronge.demo.service.ReplyService;
import com.ronge.demo.utils.JackSonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liurong
 * @date 2020/10/4 21:09
 */
@RequestMapping(value="/rest/v1/reply")
@RestController
public class ReplyController {

    private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @Autowired
    private ReplyService replyService;


    @PostMapping("/add")
    public Object insertReply(@RequestBody String replyString){
        Map<String, Object> map = new HashMap<>();
        try {
            Reply reply = (Reply) JackSonUtils.convertStringToObject(replyString,Reply.class);
            replyService.addReply(reply);
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("添加回复异常:{}",e);
            map.put(ResponseContants.STATUS,ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE,e.getMessage());
        }
        return map;
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteReplyById(@PathVariable("id") int id){
        Map<String, Object> map = new HashMap<>();
        try {
            replyService.deleteReplyById(id);
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("删除回复异常:{}",e);
            map.put(ResponseContants.STATUS,ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE,e.getMessage());
        }
        return map;
    }

    @GetMapping("/getItemsByCommentId")
    public Object getItemsByArticleId(@RequestParam("commentId") int commentId){
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(ResponseContants.DATA, replyService.getItemsByCommentId(commentId));
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("获取评论回复异常:{}", e);
            map.put(ResponseContants.STATUS, ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return map;
    }



    @PostMapping("/doAction")
    public Object getItemsByArticleId(@RequestBody String param){
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = (Map<String, Object>) JackSonUtils.convertStringToObject(param, Map.class);
            int commentId = Integer.parseInt(String.valueOf(paramMap.get("replyId")));
            String operateType = String.valueOf(paramMap.get("operateType"));
            replyService.doAction(commentId, operateType);
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("获取文章评论异常:{}", e);
            map.put(ResponseContants.STATUS, ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return map;
    }

}
