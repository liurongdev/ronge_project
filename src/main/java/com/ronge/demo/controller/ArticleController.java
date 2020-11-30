package com.ronge.demo.controller;


import com.ronge.demo.constant.ResponseContants;
import com.ronge.demo.model.Article;
import com.ronge.demo.service.ArticleService;
import com.ronge.demo.utils.JackSonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/rest/v1/article")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private final int DEFAULT_RELATE_ARTICLE_SIZE = 20;


    @Autowired
    private ArticleService articleService;


    @PostMapping("/add")
    public Object insertArticle(@RequestBody String articleString){
        Map<String, Object> map = new HashMap<>();
        try {
            Article article = (Article) JackSonUtils.convertStringToObject(articleString,Article.class);
            articleService.addArticle(article);
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
            logger.info("文章发布成功...");
        } catch (Exception e) {
            logger.error("插入文章异常:{}",e);
            map.put(ResponseContants.STATUS,ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE,e.getMessage());
        }
       return map;
    }


    @GetMapping("/getArticleByUserId")
    public Object getArticleByUserId(@RequestParam(value="userId") long userId){
        Map<String, Object> map = new HashMap<>();
        try {
             map.put(ResponseContants.DATA,articleService.getItemsByUserId(userId));
             map.put(ResponseContants.STATUS,ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("查询用户发布文章异常:{}",e);
            map.put(ResponseContants.STATUS,ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE,e.getMessage());
        }
        return map;
    }

    @GetMapping("/getItemById/{id}")
    public Object getItemById(@PathVariable(value = "id") long articleId){
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(ResponseContants.DATA, articleService.getItemById(articleId));
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("根据文章id获取文章异常:{}", e);
            map.put(ResponseContants.STATUS, ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return map;
    }

    @GetMapping("/getItemByCategory")
    public Object getItemByCategory(@RequestParam(value="label") String label,
                                    @RequestParam(value="subLabel",required = false,defaultValue = "") String subLabel,
                                    @RequestParam(value="orderKey",required = false,defaultValue = "") String orderKey){
        Map<String, Object> map = new HashMap<>();
        try {
            List<Article> dataList = articleService.getItemsByCategory(label, subLabel, orderKey);
            map.put(ResponseContants.DATA, dataList);
            map.put(ResponseContants.SIZE, dataList.size());
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("根据类别查询文章异常:{}", e);
            map.put(ResponseContants.STATUS, ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return map;
    }

    @PostMapping("/doOrUndoLike")
    public Object doOrUndoLike(@RequestBody String param){
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> paramMap = (Map<String, Object>) JackSonUtils.convertStringToObject(param, Map.class);
            long articleId = Long.parseLong(String.valueOf(paramMap.get("articleId")));
            String operateType = String.valueOf(paramMap.get("operateType"));
            articleService.doOrUndoLike(articleId, operateType);
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("点赞或取消点赞异常:{}", e);
            map.put(ResponseContants.STATUS, ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return map;
    }

    @GetMapping("/getRelateArticles")
    public Object getRelateArticles(@RequestParam("articleId") long articleId,
                                    @RequestParam(value = "size", required = false) int size) {
        Map<String, Object> map = new HashMap<>();
        try {
            logger.info("getRelateArticles: articleId:{},size:{}", articleId, size);
            size = size == 0 ? DEFAULT_RELATE_ARTICLE_SIZE : size;
            List<Article> relateArticleList = articleService.getRelateArticleList(articleId, size);
            map.put(ResponseContants.DATA, relateArticleList);
            map.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("获取相关文章失败:{}", e);
            map.put(ResponseContants.STATUS, ResponseContants.FAIL);
            map.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return map;
    }
}
