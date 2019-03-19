package com.service;

import java.util.Map;

import com.bean.Role;
import com.bean.User;

public interface ILoginService {
	 User addUser(Map<String, Object> map);
	 Role addRole(Map<String, Object> map);
	 User findByName(String name);
}
