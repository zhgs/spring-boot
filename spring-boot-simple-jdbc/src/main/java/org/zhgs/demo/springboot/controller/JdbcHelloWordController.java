package org.zhgs.demo.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcHelloWordController {



    /*
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    */

    @Autowired
    @Qualifier("appJdbcTemplate")
    private JdbcTemplate appJdbcTemplate;

    @RequestMapping("list")
    public List<Map<String, Object>> index(){

        List<Map<String, Object>> resultList =  appJdbcTemplate.queryForList("select * from user");
        return resultList;
    }

    /**
     * 自定义DataSource
     * @return
     */
    @RequestMapping("self-datasource-list")
    public List<Map<String, Object>> selfDataSourceList(){
        List<Map<String, Object>> resultList =  appJdbcTemplate.queryForList("select * from user");
        return resultList;

    }

}
