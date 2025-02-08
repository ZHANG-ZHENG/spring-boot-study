package com;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class IndexController {

    // http://localhost:8080/demo/test
    @RequestMapping(value = "/test")
    public String index() {

        return "Hello World Lib Out!";
    }
}
