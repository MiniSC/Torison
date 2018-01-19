package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan({"com.torison.util","com.torison.config"})
@SpringBootApplication
@ImportResource(value = { "classpath:providers.xml" })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class ,args);
    }
}
