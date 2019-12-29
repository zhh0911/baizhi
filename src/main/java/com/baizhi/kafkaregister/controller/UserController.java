package com.baizhi.kafkaregister.controller;

import com.baizhi.kafkaregister.entity.User;
import com.baizhi.kafkaregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("register")
    public String register(User user){
        userService.register(user);
        return "注册成功";

    }
}
