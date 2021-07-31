package com.ronge.demo.controller;

import com.ronge.demo.constant.ResponseContants;
import com.ronge.demo.model.Comment;
import com.ronge.demo.service.CommentService;
import com.ronge.demo.utils.JackSonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liurong
 * @date 2020/10/4 19:58
 */
@RequestMapping(value = "/rest/v1/comment")
@RestController
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public Object insertComment(@RequestBody String commentString) {
        Map<String, Object> map = new HashMap<>();
        try {
            Comment comment = (Comment) JackSonUtils.convertStringToObject(commentString, Comment.class);
            commentService.addComment(comment);
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("添加评论异常:{}", e);
            map.put(ResponseContants.STATUS, ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return map;
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteCommentById(@PathVariable("id") int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            commentService.deleteCommentById(id);
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("删除评论异常:{}", e);
            map.put(ResponseContants.STATUS, ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return map;
    }


    @GetMapping("/getItemsByArticleId")
    public Object getItemsByArticleId(@RequestParam("articleId") long articleId,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(ResponseContants.DATA, commentService.getItemsByArticleId(articleId));
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("获取文章评论异常:{}", e);
            map.put(ResponseContants.STATUS, ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return map;
    }


    @PostMapping("/doAction")
    public Object getItemsByArticleId(@RequestBody String param) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = (Map<String, Object>) JackSonUtils.convertStringToObject(param, Map.class);
            int commentId = Integer.parseInt(String.valueOf(paramMap.get("commentId")));
            String operateType = String.valueOf(paramMap.get("operateType"));
            commentService.doAction(commentId, operateType);
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("获取文章评论异常:{}", e);
            map.put(ResponseContants.STATUS, ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return map;
    }
}
