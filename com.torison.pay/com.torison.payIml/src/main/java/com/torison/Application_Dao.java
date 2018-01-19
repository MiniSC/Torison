package com.torison;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.torison.dao")
public class Application_Dao {
public static void main(String[] args) {
            SpringApplication.run(Application_Dao.class,args);
        }
}
