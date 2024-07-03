package com.itheima.mapper;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where user_email=#{email} and user_password=#{password} and user_status=0")
    @Results(id = "userMap",value = {
            @Result(column = "user_id", property = "id"),
            @Result(column = "user_name", property = "name"),
            @Result(column = "user_password", property = "password"),
            @Result(column = "user_email", property = "email"),
            @Result(column = "user_role", property = "role"),
            @Result(column = "user_status", property = "status"),
    })
    public User login(User user);
}
