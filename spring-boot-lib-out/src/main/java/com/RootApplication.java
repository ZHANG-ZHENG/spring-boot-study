package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RootApplication {

    /**
     * java -cp lib-out-1.0.0.jar;libs/* org.springframework.boot.loader.JarLauncher
     * java -Dloader.path=./libs -jar lib-out-1.0.0.jar
     * @param args
     */
    public static void main(String [] args) {
        SpringApplication.run(RootApplication.class, args);
    }
}
