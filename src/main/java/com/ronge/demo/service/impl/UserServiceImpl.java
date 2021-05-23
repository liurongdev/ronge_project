package com.ronge.demo.service.impl;

import com.ronge.demo.dao.UserDao;
import com.ronge.demo.model.Article;
import com.ronge.demo.model.User;
import com.ronge.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger loggger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final int DEFAULT_HOT_AUTHOR_SIZE = 3;

    private static final String DEFAULT_ORDER_KEY="createTime";

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User findUserById(int id) {
        return userDao.findUserByName("root");
    }

    @Override
    public List<User> getHotUsers(String label, int size) {
        if (size == 0) {
            size = DEFAULT_HOT_AUTHOR_SIZE;
        }
        List<User> userList = userDao.getHotUser(label, size);
        return userList;
    }

    @Override
    public List<Article> getArticles(String userId, int start, int count, String orderKey) {
        if (StringUtils.isBlank(orderKey)) {
            orderKey = DEFAULT_ORDER_KEY;
            if (loggger.isInfoEnabled()) {
                loggger.info("getArticles use default order key ,userId={},orderKey={}", userId, orderKey);
            }
        }
        List<Article> articleList = userDao.getArticles(userId, start, count, orderKey);
        return articleList;
    }


}
