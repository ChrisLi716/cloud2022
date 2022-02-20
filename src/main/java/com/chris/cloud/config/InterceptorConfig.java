package com.chris.cloud.config;

import com.chris.cloud.interceptor.MyInterceptor1;
import com.chris.cloud.interceptor.MyInterceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    MyInterceptor1 myInterceptor1;

    @Autowired
    MyInterceptor2 myInterceptor2;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor1).addPathPatterns("/hello/*").excludePathPatterns("/error/*").order(1);
        registry.addInterceptor(myInterceptor2).addPathPatterns("/hello/*", "/user/**").excludePathPatterns("/error" + "/*").order(2);
    }
}
