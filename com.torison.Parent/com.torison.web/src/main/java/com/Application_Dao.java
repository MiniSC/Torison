package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@SpringBootApplication
@ComponentScan({"com.test.controller"})
public class Application_Dao {
public static void main(String[] args) {
            SpringApplication.run(Application_Dao.class,args);
        }
}
