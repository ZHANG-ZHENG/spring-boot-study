package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * docker run -d  -v /build/spring-boot-study-demo/:/usr/local/app -p 443:8443 --name demo-test-s moxm/java:1.8-full  java -jar -server -Xms64M -Xmx256M /usr/local/app/demo-https-1.0.0.jar
 */
@RestController
@RequestMapping("/demo")
public class IndexController {

    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // https://localhost:8443/demo/test
    @RequestMapping(value = "/test")
    public Map<String, Object> test(String query) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        Date now = new Date();

        Map<String, Object> result = new HashMap<>();
        result.put("query", query);
        result.put("result", sdf.format(now));
        return result;
    }

    // https://localhost:8080/demo/query/baidu.com
    @GetMapping("/query/{query}")
    public Map<String, Object> test2(@PathVariable String query) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        Date now = new Date();

        Map<String, Object> result = new HashMap<>();
        result.put("query", query);
        result.put("result", sdf.format(now));
        return result;
    }

    // https://localhost:8443/demo/size?size=10&fix=a
    @RequestMapping(value = "/size")
    public Map<String, Object> size(Long size, String fix) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        Date now = new Date();

        Map<String, Object> result = new HashMap<>();

        result.put("result", sdf.format(now));
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for(int i=0; i<size; i++){
            if (fix != null ){
                sb.append(fix);
            } else {
                int index = r.nextInt(ALPHA_NUMERIC.length());
                sb.append(ALPHA_NUMERIC.charAt(index));
            }
        }
        result.put("query", sb.toString());
        return result;
    }
}
