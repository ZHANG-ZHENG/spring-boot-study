package com.ruijie.ioc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ruijie.ioc.bean.Student;




@Service
public class StudentService extends BaseService<Student> {
	@Autowired
	private StudentService studentDao;
	@Cacheable(value = "studentcache",keyGenerator = "wiselyKeyGenerator") 
	public Student findStudentByRedis(Integer id){
		System.out.println("无缓存的时候调用这里");
		Student queryStudent = new Student();
		queryStudent.setId(id);
		List<Student> list = studentDao.queryByObject(queryStudent);
		return list.get(0);
	}
	
}
