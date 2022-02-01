package com.chris.cloud.bean.processor.config;

import com.chris.cloud.bean.processor.service.IHelloService;
import com.chris.cloud.bean.processor.service.impl.HelloServiceImpl1;
import com.chris.cloud.bean.processor.service.impl.HelloServiceImpl2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitBeanConfig {


    @Bean(initMethod = "initName")
    public IHelloService helloServiceImpl_1() {
        return new HelloServiceImpl1();
    }

    @Bean(initMethod = "initName")
    public IHelloService helloServiceImpl_2() {
        return new HelloServiceImpl2();
    }
}
