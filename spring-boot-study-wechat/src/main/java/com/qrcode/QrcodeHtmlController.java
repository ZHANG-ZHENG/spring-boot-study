package com.qrcode;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "icweixin")
public class QrcodeHtmlController {  
   

	@GetMapping("qrcode")
	private String index(HttpServletRequest request) {
		//request.setAttribute("key", "zz");
		return "qrcode";	
	}
    
   
}
