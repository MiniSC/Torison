package com.torison;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ComponentScan({"com.torison.JPA"})
@ImportResource(value = { "classpath:providers.xml" })
public class PayApplication {
public static void main(String[] args) {
            SpringApplication.run(PayApplication.class,args);
        }
}
