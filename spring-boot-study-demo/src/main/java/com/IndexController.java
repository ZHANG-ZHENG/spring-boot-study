package com;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/demo")
public class IndexController {

    // http://localhost:8080/demo/test
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
