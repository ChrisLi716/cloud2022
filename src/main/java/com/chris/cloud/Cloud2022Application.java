package com.chris.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class Cloud2022Application {

    public static void main(String[] args) {
        SpringApplication.run(Cloud2022Application.class, args);
    }

}
