package com.mapper.db1;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.dynamic.datasource.annotation.DS;



@Repository
public interface UserMapper {


    @Select("select count(1) from user")
    int count();
}
