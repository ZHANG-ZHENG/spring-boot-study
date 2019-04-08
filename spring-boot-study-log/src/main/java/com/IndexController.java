package com;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class IndexController {
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
    @RequestMapping(value = "/test")
    public String index() {
    	logger.info("Hello World spring-boot-study-demo info");
        return "Hello World spring-boot-study-demo!!!";
    }
    
    @RequestMapping(value = "/test2")
    public String index2() {
    	logger.warn("Hello World spring-boot-study-demo warn");
        return "Hello World spring-boot-study-demo!!!";
    }
    
    @RequestMapping(value = "/test3")
    public String index3() {
    	logger.error("Hello World spring-boot-study-demo error");
        return "Hello World spring-boot-study-demo!!!";
    }
}
