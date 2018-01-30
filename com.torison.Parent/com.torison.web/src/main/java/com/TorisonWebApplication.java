package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com","com.common.config"})
public class TorisonWebApplication {
public static void main(String[] args) {
            SpringApplication.run(TorisonWebApplication.class,args);
        }
}
