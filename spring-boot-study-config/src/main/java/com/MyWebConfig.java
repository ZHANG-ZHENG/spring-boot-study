package com;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


//@ConfigurationProperties(locations = "classpath:config/my-web.properties", prefix = "web")
@PropertySource("classpath:config/my-web.properties")
@ConfigurationProperties(prefix = "web")
@Component
public class MyWebConfig {

    private String name;

    private String version;

    private String author;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
