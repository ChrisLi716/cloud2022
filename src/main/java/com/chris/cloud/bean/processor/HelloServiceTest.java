package com.chris.cloud.bean.processor;

import com.chris.cloud.bean.processor.service.IHelloService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class HelloServiceTest {

    @RoutingInjected
    IHelloService helloService;

}