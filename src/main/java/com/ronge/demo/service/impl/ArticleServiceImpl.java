package com.ronge.demo.service.impl;

import com.ronge.demo.dao.ArticleDao;
import com.ronge.demo.model.Article;
import com.ronge.demo.service.ArticleService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private final String DO_LIKE = "do";

    private final String UNDO_LIKE = "undo";


    @Autowired
    private ArticleDao articleDao;

    @Override
    public boolean addArticle(Article article) {
        article.setId(System.currentTimeMillis());
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setAuthorId("15603006298");
        return articleDao.addArticle(article);
    }

    @Override
    public Article getItemById(long id) {
        return articleDao.getItemById(id);
    }

    @Override
    public List<Article> getItemsByUserId(long userId) {
        return articleDao.getItemsByUserId(userId);
    }

    @Override
    public List<Article> getItemsByCategory(String label, String subLabel,String orderKey) {
        List<Article> articleList = articleDao.getItemsByCategory(label,subLabel,orderKey);
        handle(articleList);
        return articleList;
    }

    @Override
    public void doOrUndoLike(long articleId,String operateType) {
        if (Objects.equals(operateType, DO_LIKE)) {
            articleDao.doLike(articleId);
        } else if (Objects.equals(operateType, UNDO_LIKE)) {
            articleDao.undoLike(articleId);
        } else {
            throw new IllegalArgumentException("please input illegal param: do or undo....");
        }
    }

    @Override
    public List<Article> getRelateArticleList(long articleId, int limitSize) {
        List<Article> articleList = articleDao.getRelateArticleList(articleId, limitSize);
        return articleList;
    }

    /**
     * 根据文章id获取用户相关的统计信息
     * 发表的文章数据，获赞数目，评论数等。
     * @param articleId
     * @return
     */
    @Override
    public Map<String, Object> getUserInfoByArticleId(long articleId) {
        Map<String, Object> res = articleDao.getUserInfoByArticleId(articleId);
        return res;
    }



    public void handle(List<Article> articles){
        if(CollectionUtils.isEmpty(articles)){
            return;
        }
        for(Article article:articles){
            long now = System.currentTimeMillis();
            long distanceMills = now-article.getCreateTime().getTime();
            long milli = 1000;
            String res;
            if (distanceMills <= milli * 60) {
                res = "刚刚";
            } else if (distanceMills < milli * 60 * 60) {  //分钟级别
                res = distanceMills / (milli * 60) + "分钟前";
            } else if (distanceMills < milli * 60 * 60 * 24) {
                res = distanceMills / (milli * 60 * 60) + "小时前";
            } else {
                res = distanceMills / (milli * 60 * 60 * 24) + "天前";
            }
            article.setAliasCreateTime(res);
        }
    }
}
