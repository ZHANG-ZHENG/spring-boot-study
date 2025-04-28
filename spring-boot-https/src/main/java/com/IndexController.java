package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * docker run -d  -v /build/spring-boot-study-demo/:/usr/local/app -p 443:8443 --name demo-test moxm/java:1.8-full  java -jar -server -Xms64M -Xmx256M /usr/local/app/demo-https-1.0.0.jar
 */
@RestController
@RequestMapping("/demo")
public class IndexController {

    // http://localhost:8443/demo/test
    @RequestMapping(value = "/test")
    public Map<String, Object> test(String query) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        Date now = new Date();

        Map<String, Object> result = new HashMap<>();
        result.put("query", query);
        result.put("result", sdf.format(now));
        return result;
    }

    // http://localhost:8080/demo/query/baidu.com
    @GetMapping("/query/{query}")
    public Map<String, Object> test2(@PathVariable String query) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        Date now = new Date();

        Map<String, Object> result = new HashMap<>();
        result.put("query", query);
        result.put("result", sdf.format(now));
        return result;
    }
}
