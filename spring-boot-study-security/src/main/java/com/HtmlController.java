package com;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {


	@RequestMapping("/")
	public String index() {
	    return "index";
	}
	@PreAuthorize("hasAuthority('hello')") 
	@RequestMapping("/hello")
	public String hello() {
	    return "hello";
	}
	 
	@RequestMapping("/login")
	public String login() {
	    return "login";
	
	}
//	@RequestMapping("/error")
//	public String error() {
//	    return "error";
//	
//	}
}