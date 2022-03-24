package com.mapper.db1;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;



@Repository
public interface UserMapper {


    @Select("select count(1) from user")
    int count();
}
