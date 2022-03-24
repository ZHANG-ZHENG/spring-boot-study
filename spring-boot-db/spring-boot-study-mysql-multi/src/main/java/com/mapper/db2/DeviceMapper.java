package com.mapper.db2;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;



@Repository
public interface DeviceMapper {


    @Select("select count(1) from device")
    int count();
}
