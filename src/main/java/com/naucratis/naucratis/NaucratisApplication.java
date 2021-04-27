package com.naucratis.naucratis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class NaucratisApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaucratisApplication.class, args);
    }

}
