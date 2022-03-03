package com.chris.cloud.processor;

import com.chris.cloud.processor.service.IHelloService;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class HelloServiceTest {

    private String name = "HelloServiceTest";

    @RoutingInjected
    IHelloService helloService;

}