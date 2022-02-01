package com.chris.cloud.bean.processor.service.impl;

import com.chris.cloud.bean.processor.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class HelloServiceImpl1 implements IHelloService {

    public HelloServiceImpl1() {
        log.info("construct HelloServiceImpl1 success");
    }

    private String name;


    @Override
    public void sayHello() {
        System.out.println("hello, this is " + name);
    }

    @Override
    public void afterPropertiesSet() {
        this.name = "HelloServiceImpl1";
        log.info("do afterPropertiesSet success, name:{}", name);
    }

    public void initName() {
        this.name = "HelloServiceImpl1";
        log.info("init name success, name:{}", name);
    }

    @PostConstruct
    public void init() {
        this.name = "HelloServiceImpl1";
        log.info("do post construct success, name:{}", name);
    }

}
