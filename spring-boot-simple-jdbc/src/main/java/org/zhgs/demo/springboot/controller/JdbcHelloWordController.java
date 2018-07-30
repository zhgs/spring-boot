package org.zhgs.demo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcHelloWordController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("list")
    public List<Map<String, Object>> index(){


        List<Map<String, Object>> resultList =  jdbcTemplate.queryForList("select * from user");


        return resultList;

    }
}
