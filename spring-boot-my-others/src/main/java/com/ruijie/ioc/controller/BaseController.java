package com.ruijie.ioc.controller;



import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruijie.ioc.dao.tool.Page;
import com.ruijie.ioc.service.BaseService;

@RestController
@RequestMapping("/base")
public class BaseController<T> {

	@Autowired
	private BaseService<T> baseService;

	

	@RequestMapping("/addObject")
	public String addObject(T object) {
		baseService.addObject(object);
		return "ok";
	}	
	@RequestMapping("/deleteObject")
	public String deleteObject(T object) {
		baseService.deleteObject(object);
		return "ok";
	}
	@RequestMapping("/updateObject")
	public String updateObject(T object) {
		baseService.updateObject(object);
		return "ok";
	}	
	@RequestMapping("/findByObject")
	public T findByObject(T queryObject){
		return baseService.findByObject(queryObject);
	}
	@RequestMapping("/queryByObject")
	public List<T> queryByObject(T queryObject) {
		System.out.println("queryByObject");
		return baseService.queryByObject(queryObject);
	}
	//@PostMapping("/queryByObjectPage")
	//@RequestMapping(value="/queryByObjectPage")
	@RequestMapping(value="/queryByObjectPage")
	public Page<T> queryByObjectPage(Integer page,Integer pageSize,T queryObject) {
		System.out.println(page+" "+pageSize);
		return baseService.queryByObjectPage(page, pageSize, queryObject);
	}
	@RequestMapping("/test")
	public String test() {
		baseService.test();
		return "base test";
	}
//	/**
//	 * 获取T的类型
//	 * @return
//	 */
//	protected Class<?> getclassT() {
//    	ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
//    	//System.out.println(type.getActualTypeArguments()[0]);
//    	Class<?> classT =  (Class<?>) type.getActualTypeArguments()[0];
//    	return classT;
//	}
}
