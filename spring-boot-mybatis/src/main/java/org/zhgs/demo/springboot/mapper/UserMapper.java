package org.zhgs.demo.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.zhgs.demo.springboot.domain.User;

import java.util.List;

//@Mapper
@Component
public interface UserMapper {

    @Select("select * from user")
    List<User> getAllUsers();
}
