package com.bookross.mainservice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BookrossApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookrossApplication.class, args);
    }

}
