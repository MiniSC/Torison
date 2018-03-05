package com.torison;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com"})
public class PayApplication {
public static void main(String[] args) {
            SpringApplication.run(PayApplication.class,args);
        }
}
