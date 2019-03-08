package com.ruijie.ioc.service;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;

import com.ruijie.ioc.dao.BaseDao;
import com.ruijie.ioc.dao.tool.Page;


/**
 * 增删改查基类
 * 
 * @param <T>
 * @param <ID>
 */
@Service
public class BaseService<T> {



	@Autowired
	private BaseDao<T> baseDao;
	
	public void addObject(T object){
		baseDao.addObject(object);
	}
	
	public void deleteObject(T object){
		baseDao.deleteObject(object);
	}
	public void updateObject(T object){
		baseDao.updateObject(object);
	}	
	public T findByObject(T queryObject){
		return baseDao.findByObject(queryObject);
	}
	public List<T> queryByObject(T queryObject){
		return baseDao.queryByObject(queryObject);
	}
	public Page<T> queryByObjectPage(Integer currentPage,Integer numPerPage,T queryObject){
		return baseDao.queryByObjectPage(currentPage,numPerPage,queryObject);
	}
	public void test(){
		baseDao.test();
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
