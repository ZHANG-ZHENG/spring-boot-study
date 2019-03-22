package com;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robot.QuestionUtil;


@RestController
@RequestMapping(value = "icweixin/auth")
public class AuthController{


	@GetMapping("test")
	private String test(HttpServletRequest request) {
		System.out.println("test");
        return "test";
    }  
	@GetMapping("test2")
	private String test2(HttpServletRequest request) {
		System.out.println("test");
        return "test 你好 "+QuestionUtil.getAnswer("福州天气");
    }  
	/**
	 * 公众号设置-业务域名
	 * 1e484531y2.imwork.net/icweixin/auth
	 * iclasstest.ruijie.com.cn/icweixin/auth
	 */	
	@RequestMapping("MP_verify_7xCWZCDXEGfRMf6q.txt")
	public String exportFile(HttpServletResponse response) {
		return "7xCWZCDXEGfRMf6q";		
	}	
//	/**
//	 * 公众号设置-业务域名
//	 * 1e484531y2.imwork.net/icweixin/auth/servicefile/MP_verify_7xCWZCDXEGfRMf6q.txt
//	 * 1e484531y2.imwork.net/icweixin/auth/servicefile
//	 * iclasstest.ruijie.com.cn/icweixin/auth/servicefile/MP_verify_7xCWZCDXEGfRMf6q.txt
//	 * iclasstest.ruijie.com.cn/icweixin/auth/servicefile
//	 */	
//	@RequestMapping("servicefile/MP_verify_7xCWZCDXEGfRMf6q.txt")
//	public void exportFile2(HttpServletResponse response) {
//		System.out.println("servicefile");
//		response.setHeader("content-type", "application/octet-stream");
//		response.setContentType("application/octet-stream");
//		response.setHeader("Content-Disposition", "attachment;filename=MP_verify_7xCWZCDXEGfRMf6q.txt");
//	    try {
//	    	OutputStream out = response.getOutputStream();
//	    	out.write("7xCWZCDXEGfRMf6q".getBytes());
//	    	out.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}		
//	}	
}
