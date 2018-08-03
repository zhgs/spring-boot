package org.zhgs.demo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhgs.demo.springboot.domain.User;
import org.zhgs.demo.springboot.mapper.master.UserMapperMaster;
import org.zhgs.demo.springboot.mapper.slave.UserMapperSlave;

import java.util.List;

@RestController
public class MybatisController {

    @Autowired
    private UserMapperMaster userMapperMaster;

    @Autowired
    private UserMapperSlave userMapperSlave;

    /**
     * http://localhost:8080/index
     * @return
     */
    @RequestMapping("index")
    public List<User>  index(){

        List<User> usersMaster = userMapperMaster.getAllUsers();

        List<User> usersSlave = userMapperSlave.getAllUsers();

        usersMaster.addAll(usersSlave);

        return usersMaster;

    }


}
