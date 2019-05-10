package com;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(value = "/test")
    public String index() {

        return "Hello World spring-boot-study-demo!!!";
    }
    @RequestMapping(value = "/test2")
    public String index2() {

        return "test2 Hello World spring-boot-study-demo!!!";
    }
    
    @PreAuthorize("hasAuthority('download')") 
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse res) {

	    res.setHeader("content-type", "application/octet-stream");
	    res.setContentType("application/octet-stream");
	    res.setHeader("Content-Disposition", "attachment;filename=" + "test.txt");
	    OutputStream os = null;
	    try {
			os = res.getOutputStream();
			os.write("hello".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
