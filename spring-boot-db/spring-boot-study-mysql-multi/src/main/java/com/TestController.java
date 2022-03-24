package com;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapper.db1.UserMapper;
import com.mapper.db2.DeviceMapper;

@RestController
@RequestMapping("/mysql")
public class TestController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    
    @RequestMapping("/db")
    public String get(){

        return "查询数据记录1:"+userMapper.count()+" 查询数据记录2:"+deviceMapper.count();
    }
    

}
