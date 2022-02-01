package com.chris.cloud.bean.processor;

import com.chris.cloud.bean.processor.service.IHelloService;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class HelloServiceTest {

    @RoutingInjected
    IHelloService helloService;

}