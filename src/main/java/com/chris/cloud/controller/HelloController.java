package com.chris.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    @GetMapping("/sayHello")
    public String sayHello() {
        log.info("HelloController.sayHello executed");
        return "hello My HandlerInterceptor";
    }

}
