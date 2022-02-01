package com.chris.cloud;

import cn.hutool.core.collection.CollUtil;
import com.chris.cloud.bean.processor.HelloServiceTest;
import com.chris.cloud.bean.processor.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@SpringBootTest
@Slf4j
public class BeanPostProcessorTest {
    @Autowired
    HelloServiceTest helloServiceTest;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testHelloService() {
        Map<String, IHelloService> beansOfType = applicationContext.getBeansOfType(IHelloService.class);
        helloServiceTest.getHelloService().sayHello();
        if (CollUtil.isNotEmpty(beansOfType)) {
            beansOfType.forEach((k, v) -> {
                log.info("k:{}, v:{}", k, v.getClass().getName());
            });
        }

    }
}

