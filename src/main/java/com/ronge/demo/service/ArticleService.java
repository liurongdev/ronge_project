package com.ronge.demo.service;

import com.ronge.demo.model.Article;

import java.util.List;

public interface ArticleService {

    boolean addArticle(Article article);

    Article getItemById(long id);

    List<Article> getItemsByUserId(long userId);

    List<Article> getItemsByCategory(String label,String subLabel,String orderKey);

    void doOrUndoLike(long articleId,String operateType);

    List<Article> getRelateArticleList(long articleId,int limitSize);
}
