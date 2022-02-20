package com.chris.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
@Slf4j
public class ErrorController {

    @GetMapping("/showError")
    public String showError() {
        log.info("ErrorController.showError executed");
        return "the error happended";
    }

}
