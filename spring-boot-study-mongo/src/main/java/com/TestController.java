package com;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.User;
import com.dao.UserRepository;

@RestController
@RequestMapping("/mongo/test")
public class TestController {
	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping("/add")
    private String set() {
    	List<User> users = userRepository.findAll();
    	if(users.size() > 1000){
    		return "请删除数据再测试";
    	}
    	
    	Long id = System.currentTimeMillis();
    	Long startTime = System.currentTimeMillis();
    	for(int i=0;i<100;i++){
    		long uid = id + i;
    		userRepository.insert(new User(uid,"zz",0));
    	}
    	Long endTime = System.currentTimeMillis();

    	return "新增数据记录:"+100+" 用时:"+(endTime-startTime);
    }
    @RequestMapping("/get")
    private String get() {
    	Long startTime = System.currentTimeMillis();
		List<User> users = userRepository.findAll();
		System.out.println("size: "+users.size());
		Long endTime = System.currentTimeMillis();
    	return "查询数据记录:"+users.size()+" 用时:"+(endTime-startTime);
    }
    @RequestMapping("/del")
    private String del() {
		List<User> users = userRepository.findAll();
		System.out.println("查询记录"+users.size());
		Long startTime = System.currentTimeMillis();
		for(User user : users){
			userRepository.deleteById(user.getId());
		}
		Long endTime = System.currentTimeMillis();
    	return "删除数据记录:"+users.size()+" 用时:"+(endTime-startTime);
    }    
}
