package org.zhgs.demo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhgs.demo.springboot.domain.User;
import org.zhgs.demo.springboot.mapper.UserMapper;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/get-user-list")
    public List<User> queryUsers(){

        return userMapper.getAllUsers();

    }

}
