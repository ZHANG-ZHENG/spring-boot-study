package com.crud;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/redis")
public class ValController {
	//@Autowired
	private RedisUtil redisUtil;
	
    @RequestMapping("/val/set")
    private String set(String val) {
    	redisUtil.set("key",val);
    	return val+" .";
    }
    @RequestMapping("/val/get")
    private String get(HttpSession session) {

    	String val = redisUtil.get("key").toString();
    	return val;
    }
}
