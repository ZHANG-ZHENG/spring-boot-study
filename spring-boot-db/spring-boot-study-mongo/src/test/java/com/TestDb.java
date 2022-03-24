package com;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.User;
import com.dao.UserRepository;
 

 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=MongoDemoApp.class)// 指定spring-boot的启动类 
public class TestDb {
 
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 插入一条记录
	 */
	@Test
	public void TestSave(){	
		userRepository.insert(new User(1003L,"appl2z",22));
		System.out.println("保存成功！");
	}
	
	/**
	 * 查询全部User实体
	 */
	@Test
	public void TestFindAll(){	
		List<User> users = userRepository.findAll();
		System.out.println("size: "+users.size()+","+users.get(0).getName());
	}
	
	
	/**
	 * 根据name查询User
	 */
	@Test
	public void TestFindByName(){	
		List<User> users = userRepository.findByName("appl2z");
		System.out.println("size: "+users.size());
	}
	
	/**
	 * 根据id删除对应User实体
	 */
//	@Test
//	public void TestDelete(){
//		userRepository.deleteById(1001L);
//		//userRepository.delete(1001L);
//		System.out.println("删除成功！");
//	}
}
