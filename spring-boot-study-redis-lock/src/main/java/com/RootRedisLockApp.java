package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RootRedisLockApp {

    public static void main(String [] args) {
        SpringApplication.run(RootRedisLockApp.class, args);
    }
}
