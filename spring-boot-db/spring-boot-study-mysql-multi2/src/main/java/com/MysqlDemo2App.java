package com;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;


@SpringBootApplication
@MapperScan("com.mapper")
public class MysqlDemo2App {

    public static void main(String [] args) {
        SpringApplication.run(MysqlDemo2App.class, args);
    }
}
