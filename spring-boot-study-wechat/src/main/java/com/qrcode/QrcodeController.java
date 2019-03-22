package com.qrcode;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "icweixin")
public class QrcodeController {  
   
	public static Map<String,String> accessKeyMap = new HashMap<String, String>();
	
	@GetMapping("getAccessKey")
	private String getAccessKey(HttpServletRequest request) {
		Random r = new Random();
		int	accessKey = r.nextInt(10000) + 10000;
		accessKeyMap.put(accessKey+"", "0");
		return accessKey+"";
	}
	@GetMapping("getAccessKeyStatus")
	private String getAccessKeyStatus(HttpServletRequest request,String accessKey) {
		System.out.println("getAccessKeyStatus "+accessKey);
		return accessKeyMap.get(accessKey+"");
	}    
	@GetMapping("updateAccessKeyStatus")
	private String updateAccessKeyStatus(HttpServletRequest request,String accessKey) {
		if(accessKeyMap.get(accessKey)==null){
			return "error"+accessKey;
		}else{
			accessKeyMap.put(accessKey+"", "1");
			return "ok "+accessKey+" 1";
		}

	}
}
