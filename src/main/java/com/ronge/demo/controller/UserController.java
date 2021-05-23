package com.ronge.demo.controller;


import com.ronge.demo.constant.ResponseContants;
import com.ronge.demo.content.ResponseData;
import com.ronge.demo.model.Article;
import com.ronge.demo.model.User;
import com.ronge.demo.model.body.UserArticesVo;
import com.ronge.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/rest/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/findUserById")
    public User findUserById(@RequestParam("id") int id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData<String> login(@RequestBody Map<String, String> user) {
        ResponseData<String> responseData = new ResponseData<>();
        if (Objects.equals("ronge", user.get("username")) && Objects.equals("ronge", user.get("password"))) {
            responseData.setCode(200);
        } else {
            responseData.setCode(100);
        }
        return responseData;
    }

    @GetMapping("/getHotAuthors")
    public Object getHotAuthors(@RequestParam(name = "label", required = false, defaultValue = "") String label,
                                @RequestParam(name = "size", required = false, defaultValue = "") int size) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put(ResponseContants.DATA, userService.getHotUsers(label, size));
            response.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("获取标签及其子标签信息失败:{}", e);
            response.put(ResponseContants.STATUS, ResponseContants.FAIL);
            response.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return response;
    }

    @PostMapping("/articles")
    public Object getUserArticles(@RequestBody @Validated UserArticesVo userArticesVo) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Article> articleList = userService.getArticles(userArticesVo.getUserId(), userArticesVo.getStart(), userArticesVo.getCount(), userArticesVo.getOrderKey());
            response.put(ResponseContants.DATA, articleList);
            response.put(ResponseContants.STATUS, ResponseContants.SUCCESS);
        } catch (Exception e) {
            logger.error("获取用户相关文章失败:{}", e);
            response.put(ResponseContants.STATUS, ResponseContants.FAIL);
            response.put(ResponseContants.MESSAGE, e.getMessage());
        }
        return response;
    }
}

