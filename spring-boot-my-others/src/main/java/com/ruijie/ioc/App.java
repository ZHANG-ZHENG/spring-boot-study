package com.ruijie.ioc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import com.test.ioca.Ioca;


@SpringBootApplication
//@EnableScheduling
public class App extends WebMvcConfigurerAdapter {
	   @Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        super.configureMessageConverters(converters);
	      
	        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
	 
	        FastJsonConfig fastJsonConfig = new FastJsonConfig();
	        fastJsonConfig.setSerializerFeatures(
	                SerializerFeature.PrettyFormat
	        );
	        fastConverter.setFastJsonConfig(fastJsonConfig);
	      
	        converters.add(fastConverter);
	    }

	
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("app启动");
//        Ioca a = new Ioca();
//        a.test();
    }
}