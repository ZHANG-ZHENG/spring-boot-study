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
public class Qrcode2Controller {  
   
	public static Map<String,String> accessKeyMap = new HashMap<String, String>();
	
	@GetMapping("getAccessKey2")
	private String getAccessKey(HttpServletRequest request) {
		Random r = new Random();
		int	accessKey = r.nextInt(10000) + 10000;
		accessKeyMap.put(accessKey+"", "0");
		return accessKey+"";
	}
	@GetMapping("getAccessKeyStatus2")
	private String getAccessKeyStatus(HttpServletRequest request,String accessKey) {
		System.out.println("getAccessKeyStatus2 "+accessKey);
		Long time = System.currentTimeMillis();
		while((System.currentTimeMillis()-time)<30000){
			if("1".equals(accessKeyMap.get(accessKey))){
				break;
			}
			try{
				Thread.sleep(100);
			}catch(Exception e){
				System.out.println(e);
			}
			
		}
		return accessKeyMap.get(accessKey+"");
	}    
	@GetMapping("updateAccessKeyStatus2")
	private String updateAccessKeyStatus(HttpServletRequest request,String accessKey) {
		if(accessKeyMap.get(accessKey)==null){
			return "error"+accessKey;
		}else{
			accessKeyMap.put(accessKey+"", "1");
			return "ok "+accessKey+" 1";
		}

	}
}
