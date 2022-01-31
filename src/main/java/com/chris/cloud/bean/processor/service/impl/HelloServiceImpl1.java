package com.chris.cloud.bean.processor.service.impl;

import com.chris.cloud.bean.processor.service.IHelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl1 implements IHelloService {

    @Override
    public void sayHello() {
        System.out.println("hello, this is HelloServiceImpl1");
    }
}
