package com.ronge.demo.service.impl;

import com.ronge.demo.dao.UserDao;
import com.ronge.demo.model.User;
import com.ronge.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{


    private static final Logger loggger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User findUserById(int id) {
        return null;
    }
}
