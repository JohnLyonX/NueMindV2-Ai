package com.nue.teamnus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.nue.teamnus.mapper")
public class TeamnusCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamnusCoreApplication.class, args);
    }
}
