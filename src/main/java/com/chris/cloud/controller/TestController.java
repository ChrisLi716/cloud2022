package com.chris.cloud.controller;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@RequestMapping("/heap/test")
@RestController
public class TestController {

    public static final Map<String, Object> map = new ConcurrentHashMap<>();

    @RequestMapping("")
    public String testHeapUsed() {
        for (int i = 0; i < 1000000; i++) {
            map.put(i + "", new Object());
        }
        return "ok";
    }

    @GetMapping("/getBean/{beanName}")
    public void getBean(@PathVariable String beanName) {
        Object bean = SpringUtil.getApplicationContext().getBean(beanName);
        if (Objects.nonNull(bean)) {
            System.out.println(beanName + " init success");
        } else {
            System.out.println(beanName + "init fail");
        }
    }
}
