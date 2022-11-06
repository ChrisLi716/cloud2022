package com.chris.cloud.config;

import com.chris.cloud.config.bean.Bean1;
import com.chris.cloud.config.bean.Bean2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Bean2Config {

    @Bean
    public Bean2 bean2() {
        return new Bean2();
    }
}
