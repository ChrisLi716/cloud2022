package com.chris.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @GetMapping("/getUserInfo/{userName}")
    public String sayHello(@PathVariable("userName") String userName) {
        log.info("UserController.getUserInfo executed");
        return userName;
    }

}
