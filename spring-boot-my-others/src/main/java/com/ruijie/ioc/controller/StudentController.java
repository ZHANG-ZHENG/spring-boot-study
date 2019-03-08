package com.ruijie.ioc.controller;



import java.util.List;
import java.util.Map;

import org.postgresql.sspi.NTDSAPIWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ruijie.ioc.bean.Student;
import com.ruijie.ioc.dao.tool.Page;
import com.ruijie.ioc.service.BaseService;
import com.ruijie.ioc.service.StudentService;







@RestController
@RequestMapping("/student")
public class StudentController extends BaseController<Student>{
	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping("/queryByStudentPage")
	public Page<Student> queryByObjectPage(@RequestBody Student student) {
		System.out.println(student.getPage()+" "+student.getPageSize());
		Page<Student> page = studentService.queryByObjectPage(student.getPage(), student.getPageSize(), student);
		return page;
	}
	
	
	@RequestMapping("/findStudentByRedis")
	public Student findStudentByRedis(Integer id) {
		return studentService.findStudentByRedis(id);
	}

}
