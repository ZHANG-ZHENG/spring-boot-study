package com.ruijie.ioc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruijie.ioc.bean.Test;
import com.ruijie.ioc.dao.TestDao;



@Service
public class TestService extends BaseService<Test> {
	@Autowired
	private TestDao testDao;
	
}
