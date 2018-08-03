package org.zhgs.demo.springboot.mapper.master;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.zhgs.demo.springboot.domain.User;

import java.util.List;

@Component
public interface UserMapperMaster {

    @Select("select * from user")
    List<User> getAllUsers();
}
