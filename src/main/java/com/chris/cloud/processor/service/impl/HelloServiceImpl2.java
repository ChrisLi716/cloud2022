package com.chris.cloud.processor.service.impl;

import com.chris.cloud.processor.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class HelloServiceImpl2 implements IHelloService {

    public HelloServiceImpl2() {
        log.info("construct HelloServiceImpl2 success");
    }

    private String name;

    @Override
    public void sayHello() {
        System.out.println("hello, this is " + name);
    }

    @Override
    public void afterPropertiesSet() {
        this.name = "HelloServiceImpl2";
        log.info("do afterPropertiesSet success, name:{}", name);
    }

    public void initName() {
        this.name = "HelloServiceImpl2";
        log.info("init name success, name:{}", name);
    }

    @PostConstruct
    public void init() {
        this.name = "HelloServiceImpl2";
        log.info("do post construct success, name:{}", name);
    }
}
