package com.chris.cloud.bean.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitBeanConfig {

    @Bean(initMethod = "setPerson")
    public Person person() {
        return new Person();
    }


}
