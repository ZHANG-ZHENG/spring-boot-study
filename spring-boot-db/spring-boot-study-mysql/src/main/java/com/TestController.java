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

@RestController
@RequestMapping("/mysql/test")
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @RequestMapping("/get")
    public String get(){
    	Long startTime = System.currentTimeMillis();
        String sql = "select * from user";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);

        Long endTime = System.currentTimeMillis();
        return "查询数据记录:"+list.size()+" 用时:"+(endTime-startTime);
    }
    
    @RequestMapping("/add")
    public String add(){
    	String sql = "select * from user";
    	List<Map<String, Object>> users =  jdbcTemplate.queryForList(sql);
    	if(users.size() > 1000){
    		return "请删除数据再测试";
    	}
        Long startTime = System.currentTimeMillis();

        
        for (int i=0;i<100;i++) {
        	String sqlAdd = "INSERT INTO user(name,age) VALUES('"+i+"',"+i+");";
        	jdbcTemplate.execute(sqlAdd);
        }
        
        Long endTime = System.currentTimeMillis();
        
        return "新增数据记录:"+100+" 用时:"+(endTime-startTime);
    }
    
    @RequestMapping("/del")
    public String del(){
    	
        String sql = "select * from user";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        Long startTime = System.currentTimeMillis();

        
        for (Map<String, Object> map : list) {
        	Set<Entry<String, Object>> entries = map.entrySet( );
        	if(entries != null) {
        		Iterator<Entry<String, Object>> iterator = entries.iterator( );
        		while(iterator.hasNext( )) {
        			Entry<String, Object> entry =(Entry<String, Object>) iterator.next( );
        			Object key = entry.getKey( );
        			Object value = entry.getValue();
        			System.out.println(key+":"+value);
        			if("id".equals(key)){
        				String sqldel = "delete from user where id='" + value + "'";
        				jdbcTemplate.execute(sqldel);
        			}
              }
        	}
        }
        
        Long endTime = System.currentTimeMillis();
        
        return "删除数据记录:"+list.size()+" 用时:"+(endTime-startTime);
    }
}
