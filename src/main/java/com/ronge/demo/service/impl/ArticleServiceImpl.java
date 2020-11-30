package com.ronge.demo.service.impl;

import com.ronge.demo.dao.ArticleDao;
import com.ronge.demo.model.Article;
import com.ronge.demo.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
        return articleDao.getItemsByCategory(label,subLabel,orderKey);
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
}
