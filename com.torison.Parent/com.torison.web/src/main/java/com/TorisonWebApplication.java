package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ComponentScan({"com","com.common.config"})
@ImportResource(value = { "classpath:consumers.xml" })
public class TorisonWebApplication {
public static void main(String[] args) {
            SpringApplication.run(TorisonWebApplication.class,args);
        }
}
