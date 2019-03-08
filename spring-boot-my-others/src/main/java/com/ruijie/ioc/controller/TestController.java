package com.ruijie.ioc.controller;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ruijie.ioc.bean.Test;
import com.ruijie.ioc.service.TestService;





@RestController
@RequestMapping("/test")
public class TestController extends BaseController<Test>{


	@Autowired
	private TestService testService;

	@RequestMapping("/test")
	public String test() {
		//testService.test();
		return "dsd";
	}

//	@RequestMapping("/queryByObject")
//	public List<Test> queryByObject(Test queryObject) {
//		System.out.println("queryByObject"+getclassT());
//		return testService.queryByObject(queryObject);
//	}


}
