package com;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    // http://localhost:8080/test
    @RequestMapping(value = "/test")
    public String index() {

        return "Hello World demo!!!";
    }
}
