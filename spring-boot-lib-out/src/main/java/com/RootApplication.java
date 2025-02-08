package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RootApplication {

    /**
     * java -cp "target/lib-out-1.0.0.jar;target/libs/*" org.springframework.boot.loader.JarLauncher
     * java -cp "lib-out-1.0.0.jar;libs/*" org.springframework.boot.loader.JarLauncher
     * java -jar lib-out-1.0.0.jar --classpath=libs/*
     * @param args
     */
    public static void main(String [] args) {
        SpringApplication.run(RootApplication.class, args);
    }
}
