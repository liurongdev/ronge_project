package com.ronge.demo.service;



import com.ronge.demo.model.User;

import java.util.List;

public interface UserService {


    List<User> getUsers();

    User findUserById(int id);
}
