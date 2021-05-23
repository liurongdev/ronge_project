package com.ronge.demo.service;



import com.ronge.demo.model.Article;
import com.ronge.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User findUserById(int id);

    List<User> getHotUsers(String label,int size);

    List<Article> getArticles(String userId, int start, int count, String orderKey);
}
