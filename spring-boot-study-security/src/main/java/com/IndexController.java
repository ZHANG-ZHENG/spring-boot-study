package com;

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
}
