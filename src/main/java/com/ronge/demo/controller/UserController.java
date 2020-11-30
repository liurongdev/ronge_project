package com.ronge.demo.controller;


import com.ronge.demo.content.ResponseData;
import com.ronge.demo.model.User;
import com.ronge.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/rest/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/getUsers",method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.getUsers();
    }


    @RequestMapping(value="/findUserById")
    public User findUserById( @RequestParam("id") int id){
        return userService.findUserById(id);
    }

    @RequestMapping(value="/login" ,method = RequestMethod.POST)
    public ResponseData<String> login(@RequestBody Map<String,String> user){
        ResponseData<String> responseData=new ResponseData<>();
        if(Objects.equals("ronge",user.get("username")) && Objects.equals("ronge",user.get("password"))){
            responseData.setCode(200);
        }else{
            responseData.setCode(100);
        }
        System.out.println("dong....");
        return responseData;

    }
}
