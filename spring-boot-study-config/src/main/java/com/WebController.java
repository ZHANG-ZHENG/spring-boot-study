package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebController {

    @Value("${test.msg}")
    private String msg;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        System.out.println(msg);
        return "The Way 1 : " + msg;
    }

    @Autowired
    private Environment env;

    @RequestMapping(value = "index2", method = RequestMethod.GET)
    public String index2() {
        System.out.println(env.getProperty("test.msg"));
        return "The Way 2 : " + env.getProperty("test.msg");
    }
}
