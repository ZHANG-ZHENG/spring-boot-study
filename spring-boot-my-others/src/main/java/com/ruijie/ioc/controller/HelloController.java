package com.ruijie.ioc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    // 省略之前的内容...

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}